package daniel.services.impl;

import daniel.entities.MonitorStatusEntity;
import daniel.exceptions.customs.ListIsEmptyException;
import daniel.repositories.MonitorStatusRepository;
import daniel.services.MonitorStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitorStatusServiceImpl implements MonitorStatusService {
    private final MonitorStatusRepository repository;

    @Override
    public Page<MonitorStatusEntity> paginateAllStatus(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MonitorStatusEntity> request = repository.findAll(pageable);
        if (request.isEmpty()) {
            throw new ListIsEmptyException("List is empty");
        }
        return request;
    }

    @Override
    public void createMonitor(MonitorStatusEntity data) {
        repository.save(data);
    }
}
