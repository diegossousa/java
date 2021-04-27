package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exceptions.DeviceNotFoundException;
import one.digitalinnovation.personapi.mapper.DeviceMapper;
import one.digitalinnovation.personapi.model.Device;
import one.digitalinnovation.personapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .map(deviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(Long id) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException(id));
        return deviceMapper.toDTO(device);
    }

    public MessageResponseDTO createDevice(DeviceDTO deviceDTO) {
        String message;
        if (verifyIfExists(deviceDTO.getId())) {
            message = "Device Already Exists With ID: " + deviceDTO.getId();
        } else {
            Device savedDevice = deviceRepository.save(deviceMapper.toModel(deviceDTO));
            message = "Created Device with ID: " + savedDevice.getId();
        }
        return responseMessageDTO(message);
    }

    public MessageResponseDTO editDevice(Long id, DeviceDTO deviceDTO) {
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

    public MessageResponseDTO deleteDeviceById(Long id) {
        String message;
        if (verifyIfExists(id)) {
            deviceRepository.deleteById(id);
            message = "Device with ID: " + id + " deleted";
        } else {
            message = "Device with ID " + id + " do not exists";
        }
        return responseMessageDTO(message);
    }

    private MessageResponseDTO responseMessageDTO(String message) {
        return MessageResponseDTO
                .builder()
                .message(message)
                .build();
    }

    private boolean verifyIfExists(Long id) {
        return deviceRepository.existsById(id);
    }
}
