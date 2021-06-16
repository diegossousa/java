package one.digitalinnovation.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.enums.FanSpeed;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String ip;

    @Valid
    @NotEmpty
    private DeviceState state;

    @Valid
    @NotEmpty
    private DeviceType type;

    @Valid
    private FanSpeed speed;

    @Valid
    private Float temperature;

    @Valid
    private Float humidity;

    @Override
    public String toString() {
        return String.join("", "{",
                "id=" + id,
                ", name='" + name,
                ", ip='" + ip,
                ", state=" + state,
                ", type=" + type,
                ", speed=" + speed,
                ", temperature=" + temperature,
                ", humidity=" + humidity,
                "}");
    }
}
