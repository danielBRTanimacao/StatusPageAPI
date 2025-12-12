package daniel.services;

import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.entities.MonitorStatusEntity;
import org.springframework.data.domain.Page;


public interface MonitorStatusService {
    Page<ResponseMonitorsDTO> paginateAllStatus(int pageNum, int pageSize);
    void createMonitor(MonitorStatusEntity data);
    void updtMonitor(MonitorStatusEntity data, Long id);
}
