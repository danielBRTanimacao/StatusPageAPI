package daniel.controllers;

import daniel.entities.MonitorStatusEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/status")
public interface MonitorStatusController {
    @GetMapping
    ResponseEntity<List<MonitorStatusEntity>> listAllServicesStatus();
}
