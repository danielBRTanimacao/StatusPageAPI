package daniel.mappers;

import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.MonitorStatusEntity;
import daniel.utils.MonitorMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = MonitorMapperHelper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MonitorStatusMapper {
    @Mapping(target = "downtimes", expression = "java( monitorMapperHelper.mapDowntimesSafe(data) )")
    ResponseMonitorsDTO toDto(MonitorStatusEntity data);
    MonitorStatusEntity toEntity(RequestMonitorsDTO data);

    @Mapping(target = "downtimes", expression = "java( monitorMapperHelper.mapIdDowntimes(data) )")
    MonitorStatusEntity toUpdateEntity(UpdateMonitorsDTO data);
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void partialUpdate(MonitorStatusEntity data, @MappingTarget MonitorStatusEntity entity);
}
