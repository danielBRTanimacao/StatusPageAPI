package daniel.mappers;

import daniel.utils.dtos.uptimes.RequestMonitorsDTO;
import daniel.utils.dtos.uptimes.ResponseMonitorsDTO;
import daniel.utils.dtos.uptimes.UpdateMonitorsDTO;
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
