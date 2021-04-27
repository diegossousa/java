package one.digitalinnovation.personapi.controllers;

import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exceptions.DeviceNotFoundException;
import one.digitalinnovation.personapi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<DeviceDTO> getDevices() {
        return deviceService.getAll();
    }

    @GetMapping("/{id}")
    public DeviceDTO getDeviceById(@PathVariable Long id) throws DeviceNotFoundException {
        return deviceService.getDeviceById(id);
    }

    @PostMapping("/device")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createDevice(@RequestBody @Valid DeviceDTO deviceDTO) {
        return deviceService.createDevice(deviceDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO editDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) {
        return deviceService.editDevice(id, deviceDTO);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO deleteDeviceById(@PathVariable Long id) {
        return deviceService.deleteDeviceById(id);
    }
}
