package one.digitalinnovation.personapi.service;

import com.google.gson.Gson;
import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.exceptions.DeviceNotFoundException;
import one.digitalinnovation.personapi.mapper.DeviceMapper;
import one.digitalinnovation.personapi.model.CommandDevice;
import one.digitalinnovation.personapi.model.Device;
import one.digitalinnovation.personapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper = DeviceMapper.INSTANCE;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDTO> getAll() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(device -> {
                    getDeviceTemperatureAndHumidity(device);
                    return deviceMapper.toDTO(device);
                })
                .collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(Long id) throws DeviceNotFoundException {
        if (verifyIfExists(id)) {
            final Device deviceFound = deviceRepository.findById(id).get();

            getDeviceTemperatureAndHumidity(deviceFound);

            return deviceMapper.toDTO(deviceFound);
        } else {
            throw new DeviceNotFoundException(id);
        }
    }

    public MessageResponseDTO createDevice(DeviceDTO deviceDTO) {
        String message;

        Assert.isNull(deviceDTO.getId(), "Device Already Exists With ID " + deviceDTO.getId());

        Device savedDevice = deviceRepository.save(deviceMapper.toModel(deviceDTO));

        message = "Created Device with ID: " + savedDevice.getId();

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
        String message;
        if (verifyIfExists(id)) {
            deviceRepository.deleteById(id);
            message = "Device with ID: " + id + " deleted";
        } else {
            message = "Device with ID " + id + " do not exists";
        }
        return responseMessageDTO(message);
    }

    public MessageResponseDTO command(CommandDevice commandDevice) throws DeviceNotFoundException {

        final Device device = deviceRepository
                .findById(commandDevice.getId())
                .orElseThrow(() -> new DeviceNotFoundException(commandDevice.getId()));

        if (device.getType() == DeviceType.toys)
            device.setSpeed(commandDevice.getFanSpeed());
        device.setState(commandDevice.getState());

        deviceRepository.save(device);

        return responseMessageDTO("Comando enviado com sucesso");
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

    private void getDeviceTemperatureAndHumidity(Device device) {
        try {
            byte[] data = new byte[39];
            final Gson gson = new Gson();

            final InetSocketAddress socket = new InetSocketAddress(device.getIp(), 8888);
            final DatagramPacket datagramPacket = new DatagramPacket(data, 39, socket);
            final DatagramSocket datagramSocket = new DatagramSocket();

            datagramSocket.send(datagramPacket);
            datagramSocket.setSoTimeout(4000);
            datagramSocket.receive(datagramPacket);

            final DeviceDTO deviceDTO = gson.fromJson(new String(data, StandardCharsets.UTF_8), DeviceDTO.class);

            device.setHumidity(deviceDTO.getHumidity());
            device.setTemperature(deviceDTO.getTemperature());
            datagramSocket.close();
        } catch (IOException exception) {
            exception.fillInStackTrace();
        }
    }
}
