package daniel.mappers;

import daniel.dtos.monitors.RequestMonitors;
import daniel.dtos.monitors.ResponseMonitors;
import daniel.entities.MonitorStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonitorStatusMapper {
    ResponseMonitors toDto(MonitorStatusEntity data);
    MonitorStatusEntity toEntity(RequestMonitors data);
}
