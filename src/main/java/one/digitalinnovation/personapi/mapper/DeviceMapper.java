package one.digitalinnovation.personapi.mapper;

import one.digitalinnovation.personapi.dto.request.DeviceDTO;
import one.digitalinnovation.personapi.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    Device toModel(DeviceDTO deviceDTO);

    DeviceDTO toDTO(Device device);
}
