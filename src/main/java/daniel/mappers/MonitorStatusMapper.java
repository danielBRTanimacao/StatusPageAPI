package daniel.mappers;

import daniel.dtos.monitors.RequestMonitors;
import daniel.dtos.monitors.ResponseMonitors;
import daniel.entities.MonitorDowntimeEntity;
import daniel.entities.MonitorStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonitorStatusMapper {
    @Mapping(target = "downtimes", expression = "java( mapDowntimesSafe(data) )")
    ResponseMonitors toDto(MonitorStatusEntity data);
    MonitorStatusEntity toEntity(RequestMonitors data);

    default List<Long> mapDowntimesSafe(MonitorStatusEntity data) {
        try {
            if (data.getDowntimes() == null) {
                return null;
            }
            return data.getDowntimes()
                    .stream()
                    .map(MonitorDowntimeEntity::getId)
                    .toList();
        } catch (org.hibernate.LazyInitializationException e) {
            return null;
        }
    }
}
