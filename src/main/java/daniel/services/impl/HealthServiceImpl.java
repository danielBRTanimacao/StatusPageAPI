package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.mappers.HealthMapper;
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

    private final HealthMapper mapper;
    private final UptimeRepository repository;

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
            updateEndpointStatus(entity, false);
            throw new ResourceAccessException(e.getMessage());
        } catch (HttpClientErrorException e) {
            updateEndpointStatus(entity, false);
            throw new HttpClientErrorException(e.getStatusCode());
        } catch (HttpServerErrorException e) {
            updateEndpointStatus(entity, false);
            throw new HttpServerErrorException(e.getStatusCode());
        }

        updateEndpointStatus(entity, true);
        return mapper.toDTO(entity);
    }

    public void updateEndpointStatus(UptimeEntity e, boolean isOn) {
        e.setPing(isOn ? 100 : 0);
        e.setOnline(isOn);
        e.setLastChecked(LocalDateTime.now());
        repository.save(e);
    }
}
