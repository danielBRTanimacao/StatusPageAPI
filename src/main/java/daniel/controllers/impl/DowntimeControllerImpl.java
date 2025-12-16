package daniel.controllers.impl;

import daniel.controllers.DowntimeController;
import daniel.entities.MonitorDowntimeEntity;
import daniel.services.DowntimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DowntimeControllerImpl implements DowntimeController {
    private final DowntimeService service;

    @Override
    public ResponseEntity<List<MonitorDowntimeEntity>> listAllDowntimes() {
        return ResponseEntity.ok().body(service.getAll());
    }
}
