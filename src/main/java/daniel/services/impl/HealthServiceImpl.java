package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.MonitorStatusEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.mappers.HealthStatusMapper;
import daniel.repositories.MonitorStatusRepository;
import daniel.services.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.naming.NoPermissionException;
import java.net.URI;
import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final HealthStatusMapper mapper;
    private final MonitorStatusRepository repository;

    @Override
    public ResponseHealthStatusDTO getStatusById(Long id) {
        MonitorStatusEntity entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Entity with id " + id + " Not found")
        );

        try {
            RestTemplate req = new RestTemplate();
            ResponseEntity<String> res = req.exchange(entity.getUrl(), HttpMethod.GET, null, String.class);
            System.out.println(res.getStatusCode());

            if (res.getStatusCode().value() == 999) {
                throw new PermissionDeniedException("Invalid configuration on header permission denied");
            }
            if (!res.getStatusCode().equals(HttpStatus.OK)) {
                entity.setOnline(false);
            }
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException(e.getMessage());
        }

        return mapper.toDTO(entity);
    }
}
