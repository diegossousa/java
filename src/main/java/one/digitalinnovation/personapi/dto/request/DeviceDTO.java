package one.digitalinnovation.personapi.dto.request;

import lombok.*;
import one.digitalinnovation.personapi.enums.DeviceState;
import one.digitalinnovation.personapi.enums.DeviceType;
import one.digitalinnovation.personapi.enums.FanSpeed;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO implements Serializable {
    private static final long serialVersionUID = 15161516548416L;

    private Long id;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private String ip;

    private Boolean onLine;

    @Valid
    @NotEmpty
    private DeviceState luz;

    @Valid
    @NotEmpty
    private DeviceType dispositivo;

    @Valid
    private FanSpeed velocidade;

    @Valid
    private Float temperatura;

    @Valid
    private Float humidade;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", descricao:'" + descricao + '\'' +
                ", ip:'" + ip + '\'' +
                ", onLine:" + onLine +
                ", luz:" + luz +
                ", dispositivo:" + dispositivo +
                ", velocidade:" + velocidade +
                ", temperatura:" + temperatura +
                ", humidade:" + humidade +
                '}';
    }
}
