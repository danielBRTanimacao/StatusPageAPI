package daniel.mappers;

import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.MonitorDowntimeEntity;
import daniel.entities.MonitorStatusEntity;
import org.hibernate.LazyInitializationException;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonitorStatusMapper {
    @Mapping(target = "downtimes", expression = "java( mapDowntimesSafe(data) )")
    ResponseMonitorsDTO toDto(MonitorStatusEntity data);
    MonitorStatusEntity toEntity(RequestMonitorsDTO data);

    MonitorStatusEntity toUpdateEntity(UpdateMonitorsDTO data);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(MonitorStatusEntity data, @MappingTarget MonitorStatusEntity entity);

    default List<Long> mapDowntimesSafe(MonitorStatusEntity data) {
        try {
            if (data.getDowntimes() == null) {
                return null;
            }
            return data.getDowntimes()
                    .stream()
                    .map(MonitorDowntimeEntity::getId)
                    .toList();
        } catch (LazyInitializationException e) {
            return null;
        }
    }
}
