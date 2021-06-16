package one.digitalinnovation.personapi.model;

import lombok.Data;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.FanSpeed;

@Data
public class CommandDevice {
    private Long id;
    private DeviceState state;
    private FanSpeed fanSpeed;
}
