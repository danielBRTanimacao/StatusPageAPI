package daniel.controllers.impl;

import daniel.controllers.HealthController;
import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.services.HealthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthControllerImpl implements HealthController {

    private final HealthService service;

    @Override
    @Transactional
    public ResponseEntity<ResponseHealthStatusDTO> getStatusEndpoint(Long id) {
        return ResponseEntity.ok().body(service.getStatusById(id));
    }
}
