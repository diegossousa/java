package one.digitalinnovation.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.DeviceType;

import javax.persistence.Enumerated;
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

    @Enumerated
    @NotEmpty
    private DeviceType type;
}
