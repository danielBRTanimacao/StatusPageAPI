package daniel.controllers.impl;

import daniel.controllers.MonitorStatusController;
import daniel.entities.MonitorStatusEntity;
import daniel.services.MonitorStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MonitorStatusControllerImpl implements MonitorStatusController {

    private final MonitorStatusService service;

    @Override
    public ResponseEntity<List<MonitorStatusEntity>> listAllServicesStatus() {
        return ResponseEntity.ok().body(service.listAll());
    }
}
