package daniel.controllers;

import daniel.dtos.monitors.RequestMonitors;
import daniel.entities.MonitorStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/api/monitors")
public interface MonitorStatusController {
    @GetMapping
    ResponseEntity<Page<MonitorStatusEntity>> listAllServicesStatus(
            @RequestParam(defaultValue = "0") int pgNum,
            @RequestParam(defaultValue = "25") int pgSize
    );
    @PostMapping
    ResponseEntity<Void> addNewService(RequestMonitors data);
}
