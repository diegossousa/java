package one.digitalinnovation.personapi.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.enums.FanSpeed;
import one.digitalinnovation.personapi.exceptions.DeviceException;
import one.digitalinnovation.personapi.exceptions.DeviceNotFoundException;
import one.digitalinnovation.personapi.mapper.DeviceMapper;
import one.digitalinnovation.personapi.model.CommandDevice;
import one.digitalinnovation.personapi.model.Device;
import one.digitalinnovation.personapi.repository.DeviceRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper = DeviceMapper.INSTANCE;
    private final JmsTemplate jmsTemplateTopic;

    public List<DeviceDTO> getAll() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream().parallel()
                .map(Device::toDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(Long id) throws Throwable {
        return deviceRepository.findById(id).orElseThrow((Supplier<Throwable>) () -> new DeviceNotFoundException(id)).toDTO();
    }

    public MessageResponseDTO createDevice(DeviceDTO deviceDTO) {
        String message;

        Assert.isNull(deviceDTO.getId(), "Device Already Exists With ID " + deviceDTO.getId());

        Device savedDevice = deviceRepository.save(deviceMapper.toModel(deviceDTO));

        message = "Created Device with ID: " + savedDevice.getId();

        jmsTemplateTopic.convertAndSend("topic.car", deviceDTO.toString());

        return responseMessageDTO(message);
    }

    public MessageResponseDTO editDevice(Long id, DeviceDTO deviceDTO) throws DeviceNotFoundException {
        String message;
        if (verifyIfExists(id)) {
            Device deviceToEdit = deviceMapper.toModel(deviceDTO);
            deviceRepository.save(deviceToEdit);
            message = "Modified Device with ID: " + deviceDTO.getId();
        } else {
            message = "Device with ID " + deviceDTO.getId() + " do not exists";
        }
        return responseMessageDTO(message);
    }

    public MessageResponseDTO deleteDeviceById(Long id) throws DeviceNotFoundException {
        if (verifyIfExists(id)) {
            deviceRepository.deleteById(id);
        }
        return responseMessageDTO("Device with ID: " + id + " deleted");
    }

    public MessageResponseDTO command(CommandDevice commandDevice) throws DeviceNotFoundException {

        Assert.isTrue(commandDevice.getCmd().equals("luz") || commandDevice.getCmd().equals("ventilador"), "Comando não reconhecido");

        final Device device = deviceRepository
                .findById(commandDevice.getId())
                .orElseThrow(() -> new DeviceNotFoundException(commandDevice.getId()));

        if (device.getType() == DeviceType.toys && commandDevice.getCmd().equals("ventilador")) {
            if (commandDevice.getFanSpeed() != null) {
                device.setSpeed(commandDevice.getFanSpeed());
            } else {
                device.setSpeed(FanSpeed.MIN);
            }
        }

        if (send(device.getIp(), commandDevice.getCmd()).equals("OK")) {
            setDeviceTemperatureHumidityStateAndSpeedToDevice(device);
            return responseMessageDTO("Comando enviado com sucesso");
        }
        return responseMessageDTO("Comando não enviado");
    }

    public void setDeviceTemperatureHumidityStateAndSpeedToDevice(Device device) {
        try {
            Device deviceInfo = getDeviceInfo(device);

            device.setHumidity(deviceInfo.getHumidity());
            device.setTemperature(deviceInfo.getTemperature());
            device.setState(deviceInfo.getState());
            device.setSpeed(deviceInfo.getSpeed());
            device.setOnLine(deviceInfo.getOnLine());

            deviceRepository.save(device);
        } catch (DeviceException exception) {
            exception.fillInStackTrace();
        }
    }

    private MessageResponseDTO responseMessageDTO(String message) {
        return MessageResponseDTO
                .builder()
                .message(message)
                .build();
    }

    private boolean verifyIfExists(Long id) throws DeviceNotFoundException {
        if (deviceRepository.existsById(id)) {
            return true;
        } else {
            throw new DeviceNotFoundException(id);
        }
    }

    private Device getDeviceInfo(Device device) throws DeviceException {
        String deviceReturned = send(device.getIp(), "inf");
        if (!deviceReturned.equals("timeOut")) {
            Device auxDev = new Gson().fromJson(deviceReturned, Device.class);
            auxDev.setOnLine(true);
            return auxDev;
        } else {
            device.setOnLine(false);
            return device;
        }
    }

    private String send(String ip, String message) {
        try {
            byte[] sendBuf = message.getBytes();
            byte[] receiveBuf = new byte[128];
            final DatagramSocket datagramSocket = new DatagramSocket();

            final DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName(ip), 8888);
            final DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);

            datagramSocket.setSoTimeout(2000);
            datagramSocket.send(sendPacket);
            datagramSocket.receive(receivePacket);
            datagramSocket.close();

            return new String(receivePacket.getData(), 0, receivePacket.getLength());

        } catch (IOException exception) {
            return "timeOut";
        }
    }
}
