package daniel.services;

import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.entities.UptimeEntity;
import org.springframework.data.domain.Page;


public interface UptimeService {
    Page<ResponseMonitorsDTO> paginateAllStatus(int pageNum, int pageSize);
    void createMonitor(UptimeEntity data);
    void updtMonitor(UptimeEntity data, Long id);
    void delById(Long id);
}
