package daniel.mappers;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.UptimeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthMapper {
    ResponseHealthStatusDTO toDTO(UptimeEntity data);
}
