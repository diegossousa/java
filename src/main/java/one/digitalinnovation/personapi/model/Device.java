package one.digitalinnovation.personapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.enums.FanSpeed;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String ip;

    @Enumerated(value = EnumType.STRING)
    private DeviceState state;

    @Enumerated(value = EnumType.STRING)
    private DeviceType type;

    @Enumerated(value = EnumType.ORDINAL)
    private FanSpeed speed;

    @Column()
    private Float temperature;

    @Column
    private Float humidity;

    public String toJson() {
        return String.join(",",
                "{'id'='" + id + "'",
                "'name'='" + name + "'",
                "'ip'='" + ip + "'",
                "'state'='" + state + "'",
                "'type'='" + type + "'",
                "'speed'='" + speed + "'}");
    }
}
