package daniel.controllers.impl;

import daniel.controllers.MonitorStatusController;
import daniel.dtos.monitors.RequestMonitors;
import daniel.entities.MonitorStatusEntity;
import daniel.services.MonitorStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonitorStatusControllerImpl implements MonitorStatusController {

    private final MonitorStatusService service;

    @Override
    public ResponseEntity<Page<MonitorStatusEntity>> listAllServicesStatus(int pgNum, int pgSize) {
        return ResponseEntity.ok().body(service.paginateAllStatus(pgNum, pgSize));
    }

    @Override
    public ResponseEntity<Void> addNewService(RequestMonitors data) {
        return null;
    }
}
