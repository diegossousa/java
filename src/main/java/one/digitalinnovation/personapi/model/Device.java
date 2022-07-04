package one.digitalinnovation.personapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.enums.FanSpeed;
import one.digitalinnovation.personapi.mapper.DeviceMapper;

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

    private Boolean onLine;

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

    public DeviceDTO toDTO(){
        return DeviceMapper.INSTANCE.toDTO(this);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", onLine=" + onLine +
                ", state=" + state +
                ", type=" + type +
                ", speed=" + speed +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }

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
