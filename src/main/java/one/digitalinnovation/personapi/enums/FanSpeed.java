package one.digitalinnovation.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FanSpeed {
    ZERO(0),
    UM(1),
    DOIS(2),
    TRES(3);

    public int speed;
}
