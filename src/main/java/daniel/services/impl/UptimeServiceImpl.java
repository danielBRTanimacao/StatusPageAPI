package daniel.services.impl;

import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.ListIsEmptyException;
import daniel.exceptions.customs.NotFoundException;
import daniel.mappers.UptimeMapper;
import daniel.repositories.UptimeRepository;
import daniel.services.UptimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UptimeServiceImpl implements UptimeService {
    private final UptimeRepository repository;
    private final UptimeMapper mapper;

    @Override
    public Page<ResponseMonitorsDTO> paginateAllStatus(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<UptimeEntity> request = repository.findAll(pageable);
        if (request.isEmpty()) {
            throw new ListIsEmptyException("List is empty");
        }
        return request.map(mapper::toDto);
    }

    @Override
    public void createMonitor(UptimeEntity data) {
        data.setOnline(true);
        data.setLastChecked(LocalDateTime.now());
        repository.save(data);
    }

    @Override
    public void updtMonitor(UptimeEntity data, Long id) {
        UptimeEntity preUpdt = repository.findById(id).orElseThrow(
                () -> new NotFoundException("MonitorStatus whit id: " + id + " not found")
        );
        mapper.partialUpdate(data, preUpdt);
        repository.save(preUpdt);
    }

    @Override
    public void delById(Long id) {
        UptimeEntity delEntity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("MonitorStatus with id " + id + " not found")
        );
        repository.delete(delEntity);
    }
}
