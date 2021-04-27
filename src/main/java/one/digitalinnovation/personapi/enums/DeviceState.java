package one.digitalinnovation.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceState {
    ON("ON"),
    OFF("OFF");

    private final String state;
}
