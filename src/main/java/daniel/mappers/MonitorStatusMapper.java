package daniel.mappers;

import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.UptimeEntity;
import daniel.utils.MonitorMapperHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = MonitorMapperHelper.class)
public interface MonitorStatusMapper {
    @Mapping(target = "downtimes", source = ".", qualifiedByName = "mapDowntimesSafe")
    ResponseMonitorsDTO toDto(UptimeEntity data);
    UptimeEntity toEntity(RequestMonitorsDTO data);

    @Mapping(target = "downtimes", source = ".", qualifiedByName = "mapIdDowntimes")
    UptimeEntity toUpdateEntity(UpdateMonitorsDTO data);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(UptimeEntity data, @MappingTarget UptimeEntity entity);
}
