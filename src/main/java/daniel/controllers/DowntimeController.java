package daniel.controllers;

import daniel.entities.MonitorDowntimeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/downtimes")
public interface DowntimeController {
    @GetMapping
    ResponseEntity<List<MonitorDowntimeEntity>> listAllDowntimes();
}
