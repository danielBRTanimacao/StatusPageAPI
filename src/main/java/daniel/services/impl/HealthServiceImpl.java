package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.mappers.HealthMapper;
import daniel.repositories.UptimeRepository;
import daniel.services.HealthService;
import daniel.utils.EndpointHealthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final UptimeRepository repository;
    private final EndpointHealthHandler handler;
    private final HealthMapper mapper;

    @Override
    public ResponseHealthStatusDTO getStatusById(Long id) {
        UptimeEntity entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Entity with id " + id + " Not found")
        );

        try {
            handler.checkEndpoint(entity);
        } catch (Exception e) {
            handler.updateEndpointStatus(entity, false);
        }

        return mapper.toDTO(entity);
    }
}
