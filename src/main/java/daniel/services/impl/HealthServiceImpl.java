package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.MonitorDowntimeEntity;
import daniel.entities.MonitorStatusEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.mappers.HealthStatusMapper;
import daniel.repositories.MonitorStatusRepository;
import daniel.services.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final HealthStatusMapper mapper;
    private final MonitorStatusRepository repository;
    private final MonitorDowntimeEntity downtimeRepository;

    @Override
    public ResponseHealthStatusDTO getStatusById(Long id) {
        MonitorStatusEntity entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Entity with id " + id + " Not found")
        );

        try {
            RestTemplate req = new RestTemplate();
            HttpStatusCode statusCode = req.exchange(
                    entity.getUrl(),
                    HttpMethod.GET,
                    null,
                    String.class
            ).getStatusCode();

            if (statusCode.value() == 999) {
                throw new PermissionDeniedException("Invalid configuration on header permission denied");
            }
            if (!statusCode.equals(HttpStatus.OK)) {
                MonitorDowntimeEntity downtime = new MonitorDowntimeEntity();
                entity.setOnline(false);
                downtime.setMonitor(entity);
                downtime.setStartTime(LocalDateTime.now());
                downtimeRepository.save(downtime);
            }
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException(e.getMessage());
        }

        return mapper.toDTO(entity);
    }
}
