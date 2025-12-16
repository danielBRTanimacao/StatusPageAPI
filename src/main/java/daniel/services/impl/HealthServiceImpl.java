package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.mappers.HealthStatusMapper;
import daniel.repositories.UptimeRepository;
import daniel.services.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final HealthStatusMapper mapper;
    private final UptimeRepository repository;
    private final MonitorDowntimeRepository downtimeRepository;

    @Override
    public ResponseHealthStatusDTO getStatusById(Long id) {
        UptimeEntity entity = repository.findById(id).orElseThrow(
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
                throw new PermissionDeniedException("Invalid configuration permission denied");
            }
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException(e.getMessage());
        } catch (HttpClientErrorException e) {
            this.updtDowntime(entity);
            throw new HttpClientErrorException(e.getStatusCode());
        } catch (HttpServerErrorException e) {
            this.updtDowntime(entity);
            throw new HttpServerErrorException(e.getStatusCode());
        }

        return mapper.toDTO(entity);
    }

    public void updtDowntime(UptimeEntity entity) {
        MonitorDowntimeEntity downtime = new MonitorDowntimeEntity();
        entity.setOnline(false);
        entity.addDowntime(downtime);
        downtime.setMonitor(entity);
        downtime.setStartTime(LocalDateTime.now());
        downtimeRepository.save(downtime);
        repository.save(entity);
    }
}
