package one.digitalinnovation.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DeviceType {

    toys("fan"),
    light("light"),
    temperature("temperature");

    private String description;
}
