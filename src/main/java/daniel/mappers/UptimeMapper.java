package daniel.mappers;

import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.UptimeEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UptimeMapper {
    ResponseMonitorsDTO toDto(UptimeEntity data);
    UptimeEntity toEntity(RequestMonitorsDTO data);

    UptimeEntity toUpdateEntity(UpdateMonitorsDTO data);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(UptimeEntity data, @MappingTarget UptimeEntity entity);
}
