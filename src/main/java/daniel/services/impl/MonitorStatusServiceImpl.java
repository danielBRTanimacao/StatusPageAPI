package daniel.services.impl;

import daniel.entities.MonitorStatusEntity;
import daniel.exceptions.customs.ListIsEmptyException;
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
        List<MonitorStatusEntity> request = repository.findAll();

        if (request.isEmpty()) {
            throw new ListIsEmptyException("List is empty");
        }
        return request;
    }
}
