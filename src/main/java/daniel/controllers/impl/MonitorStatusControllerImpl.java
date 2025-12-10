package daniel.controllers.impl;

import daniel.controllers.MonitorStatusController;
import daniel.entities.MonitorStatusEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorStatusControllerImpl implements MonitorStatusController {

    @Override
    public ResponseEntity<List<MonitorStatusEntity>> listAllServicesStatus() {
        return null;
    }
}
