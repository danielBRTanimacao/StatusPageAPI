package daniel.services.impl;

import daniel.dtos.healths.ResponseHealthStatusDTO;
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
        return null;
    }
}
