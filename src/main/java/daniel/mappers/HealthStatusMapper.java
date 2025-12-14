package daniel.mappers;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.MonitorStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthStatusMapper {
    ResponseHealthStatusDTO toDTO(MonitorStatusEntity data);
}
