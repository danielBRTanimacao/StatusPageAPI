package daniel.controllers.impl;

import daniel.controllers.MonitorStatusController;
import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.MonitorStatusEntity;
import daniel.mappers.MonitorStatusMapper;
import daniel.services.MonitorStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonitorStatusControllerImpl implements MonitorStatusController {
    private final MonitorStatusService service;
    private final MonitorStatusMapper mapper;

    @Override
    public ResponseEntity<Page<ResponseMonitorsDTO>> listAllServicesStatus(int pgNum, int pgSize) {
        return ResponseEntity.ok().body(service.paginateAllStatus(pgNum, pgSize));
    }

    @Override
    public ResponseEntity<Void> addNewService(RequestMonitorsDTO data) {
        MonitorStatusEntity entity = mapper.toEntity(data);
        service.createMonitor(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateSpecificService(UpdateMonitorsDTO data, Long id) {
        //MonitorStatusEntity entityUpdt = mapper.toUpdateEntity(data);
        MonitorStatusEntity entityUpdt = new MonitorStatusEntity();
        service.updtMonitor(entityUpdt, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
