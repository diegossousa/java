package one.digitalinnovation.personapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.DeviceType;

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
}
