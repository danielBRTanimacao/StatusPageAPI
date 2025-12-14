package daniel.controllers;

import daniel.dtos.healths.ResponseHealthStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/health")
public interface HealthController {
    @RequestMapping("/{id}")
    ResponseEntity<ResponseHealthStatusDTO> getStatusEndpoint(@PathVariable Long id);
}
