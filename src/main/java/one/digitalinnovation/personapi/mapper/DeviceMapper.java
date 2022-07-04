package one.digitalinnovation.personapi.mapper;

import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    @Mapping(source = "descricao", target = "name")
    @Mapping(source = "luz", target = "state")
    @Mapping(source = "dispositivo", target = "type")
    @Mapping(source = "velocidade", target = "speed")
    @Mapping(source = "temperatura", target = "temperature")
    @Mapping(source = "humidade", target = "humidity")
    @Mapping(source = "onLine", target = "onLine")
    Device toModel(DeviceDTO deviceDTO);

    @Mapping(source = "name", target = "descricao")
    @Mapping(source = "state", target = "luz")
    @Mapping(source = "type", target = "dispositivo")
    @Mapping(source = "speed", target = "velocidade")
    @Mapping(source = "temperature", target = "temperatura")
    @Mapping(source = "humidity", target = "humidade")
    @Mapping(source = "onLine", target = "onLine")
    DeviceDTO toDTO(Device device);
}
