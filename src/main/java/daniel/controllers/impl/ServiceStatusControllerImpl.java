package daniel.controllers.impl;

import daniel.controllers.ServiceStatusController;
import daniel.entities.ServiceStatusEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceStatusControllerImpl implements ServiceStatusController {

    @Override
    public ResponseEntity<List<ServiceStatusEntity>> listAllServicesStatus() {
        return null;
    }
}
