package daniel.services;

import daniel.entities.MonitorStatusEntity;
import org.springframework.data.domain.Page;


public interface MonitorStatusService {
    Page<MonitorStatusEntity> paginateAllStatus(int pageNum, int pageSize);
    void createMonitor(MonitorStatusEntity data);
}
