package daniel.services;

import daniel.dtos.monitors.ResponseMonitors;
import daniel.entities.MonitorStatusEntity;
import org.springframework.data.domain.Page;


public interface MonitorStatusService {
    Page<ResponseMonitors> paginateAllStatus(int pageNum, int pageSize);
    void createMonitor(MonitorStatusEntity data);
}
