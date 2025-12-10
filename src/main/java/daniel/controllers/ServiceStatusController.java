package daniel.controllers;

import daniel.entities.ServiceStatusEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/status")
public interface ServiceStatusController {
    @GetMapping
    ResponseEntity<List<ServiceStatusEntity>> listAllServicesStatus();
}
