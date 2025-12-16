package daniel.services.impl;

import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.entities.MonitorStatusEntity;
import daniel.exceptions.customs.ListIsEmptyException;
import daniel.exceptions.customs.NotFoundException;
import daniel.mappers.MonitorStatusMapper;
import daniel.repositories.MonitorDowntimeRepository;
import daniel.repositories.MonitorStatusRepository;
import daniel.services.MonitorStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MonitorStatusServiceImpl implements MonitorStatusService {
    private final MonitorStatusRepository repository;
    private final MonitorStatusMapper mapper;

    @Override
    public Page<ResponseMonitorsDTO> paginateAllStatus(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<MonitorStatusEntity> request = repository.findAll(pageable);
        if (request.isEmpty()) {
            throw new ListIsEmptyException("List is empty");
        }
        return request.map(mapper::toDto);
    }

    @Override
    public void createMonitor(MonitorStatusEntity data) {
        data.setOnline(true);
        data.setLastChecked(LocalDateTime.now());
        repository.save(data);
    }

    @Override
    public void updtMonitor(MonitorStatusEntity data, Long id) {
        MonitorStatusEntity preUpdt = repository.findById(id).orElseThrow(
                () -> new NotFoundException("MonitorStatus whit id: " + id + " not found")
        );
        mapper.partialUpdate(data, preUpdt);
        repository.save(preUpdt);
    }
}
