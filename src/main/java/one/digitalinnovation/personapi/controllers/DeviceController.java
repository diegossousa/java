package one.digitalinnovation.personapi.controllers;

import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.FanSpeed;
import one.digitalinnovation.personapi.exceptions.DeviceNotFoundException;
import one.digitalinnovation.personapi.model.CommandDevice;
import one.digitalinnovation.personapi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.createDevice(deviceDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO editDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) throws DeviceNotFoundException {
        return deviceService.editDevice(id, deviceDTO);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO deleteDeviceById(@PathVariable Long id) throws DeviceNotFoundException {
        return deviceService.deleteDeviceById(id);
    }

    @GetMapping("/command")
    public MessageResponseDTO command(@PathParam(value = "id") Long id, @PathParam("state") DeviceState state, @PathParam("fanSpeed") FanSpeed fanSpeed)
            throws DeviceNotFoundException {
        final CommandDevice commandDevice = new CommandDevice();
        commandDevice.setState(state);
        commandDevice.setId(id);
        commandDevice.setFanSpeed(fanSpeed);

        return deviceService.command(commandDevice);
    }
}
