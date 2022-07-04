package one.digitalinnovation.personapi.controllers;

import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exceptions.DeviceException;
import one.digitalinnovation.personapi.exceptions.DeviceNotFoundException;
import one.digitalinnovation.personapi.model.CommandDevice;
import one.digitalinnovation.personapi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceDTO> getDevices() {

        return deviceService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public DeviceDTO getDeviceById(@PathVariable Long id) throws Throwable {
        return deviceService.getDeviceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.createDevice(deviceDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO editDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) throws DeviceNotFoundException {
        return deviceService.editDevice(id, deviceDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO deleteDeviceById(@PathVariable Long id) throws DeviceNotFoundException {
        return deviceService.deleteDeviceById(id);
    }

    @PutMapping("/command")
    @ResponseStatus(HttpStatus.ACCEPTED)
//    public MessageResponseDTO command(@PathParam(value = "id") Long id, @PathParam("state") String state, @PathParam("fanSpeed") String fanSpeed)
    public MessageResponseDTO command(@RequestBody CommandDevice commandDevice) throws DeviceNotFoundException {
//        final CommandDevice commandDevice = new CommandDevice();
//        commandDevice.setState(state.equalsIgnoreCase("ON") ? DeviceState.ON : DeviceState.OFF);
//        commandDevice.setId(id);
//        commandDevice.setFanSpeed(FanSpeed.valueOf(fanSpeed.toUpperCase()));

        return deviceService.command(commandDevice);
    }
}
