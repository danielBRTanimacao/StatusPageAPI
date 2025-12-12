package daniel.utils;


import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.MonitorDowntimeEntity;
import daniel.entities.MonitorStatusEntity;
import daniel.repositories.MonitorDowntimeRepository;
import daniel.repositories.MonitorStatusRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.LazyInitializationException;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MonitorMapperHelper {
    private final MonitorDowntimeRepository repository;

    public List<Long> mapDowntimesSafe(MonitorStatusEntity data) {
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

    public List<MonitorDowntimeEntity> mapIdDowntimes(UpdateMonitorsDTO data) {
        if (data.downtimes() == null) {
            return null;
        }
        return repository.findAllById(data.downtimes());
    }
}
