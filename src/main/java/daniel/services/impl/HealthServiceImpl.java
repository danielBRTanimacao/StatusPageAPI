package daniel.services.impl;

import daniel.utils.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.mappers.HealthMapper;
import daniel.repositories.UptimeRepository;
import daniel.services.HealthService;
import daniel.utils.EndpointHealthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
        handler.checkEndpoint(entity);
        return mapper.toDTO(entity);
    }
}
