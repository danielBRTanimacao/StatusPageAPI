package daniel.services.impl;

import daniel.services.DowntimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DowntimeServiceImpl implements DowntimeService {
    private final MonitorDowntimeRepository repository;

    @Override
    public List<MonitorDowntimeEntity> getAll() {
        return repository.findAll();
    }
}
