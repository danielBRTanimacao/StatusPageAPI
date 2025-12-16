package daniel.services;

import daniel.entities.MonitorDowntimeEntity;

import java.util.List;

public interface DowntimeService {
    List<MonitorDowntimeEntity> getAll();
}
