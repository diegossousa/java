package one.digitalinnovation.personapi.enums;

import lombok.Getter;

@Getter
public enum FanSpeed {
    OFF(0),
    MIN(1),
    MID(2),
    MAX(3),
    ON(4);

    public int speed;

    FanSpeed(int speed) {
        this.speed = speed;
    }

    public static int getValue(String value){
        return valueOf(value).getSpeed();
    }
}
