package daniel.services.impl;

import daniel.entities.MonitorStatusEntity;
import daniel.repositories.MonitorStatusRepository;
import daniel.services.MonitorStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorStatusServiceImpl implements MonitorStatusService {
    private final MonitorStatusRepository repository;

    @Override
    public List<MonitorStatusEntity> listAll() {
        return repository.findAll();
    }
}
