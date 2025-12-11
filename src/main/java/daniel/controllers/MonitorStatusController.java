package daniel.controllers;

import daniel.dtos.monitors.RequestMonitors;
import daniel.dtos.monitors.ResponseMonitors;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/monitors")
public interface MonitorStatusController {
    @GetMapping
    ResponseEntity<Page<ResponseMonitors>> listAllServicesStatus(
            @RequestParam(defaultValue = "0") int pgNum,
            @RequestParam(defaultValue = "25") int pgSize
    );
    @PostMapping
    ResponseEntity<Void> addNewService(@Valid @RequestBody RequestMonitors data);
}
