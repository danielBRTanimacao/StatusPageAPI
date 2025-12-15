package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.MonitorStatusEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.mappers.HealthStatusMapper;
import daniel.repositories.MonitorStatusRepository;
import daniel.services.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final HealthStatusMapper mapper;
    private final MonitorStatusRepository repository;

    @Override
    public ResponseHealthStatusDTO getStatusById(Long id) {
        MonitorStatusEntity entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Entity with id" + id + " Not found")
        );
        return mapper.toDTO(entity);
    }
}
