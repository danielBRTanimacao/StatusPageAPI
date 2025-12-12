package daniel.mappers;

import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.MonitorStatusEntity;
import daniel.utils.MonitorMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = MonitorMapperHelper.class)
public interface MonitorStatusMapper {
    @Mapping(target = "downtimes", source = ".", qualifiedByName = "mapDowntimesSafe")
    ResponseMonitorsDTO toDto(MonitorStatusEntity data);
    MonitorStatusEntity toEntity(RequestMonitorsDTO data);

    @Mapping(target = "downtimes", source = ".", qualifiedByName = "mapIdDowntimes")
    MonitorStatusEntity toUpdateEntity(UpdateMonitorsDTO data);
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void partialUpdate(MonitorStatusEntity data, @MappingTarget MonitorStatusEntity entity);
}
