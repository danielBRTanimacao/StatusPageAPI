package daniel.controllers;

import daniel.utils.dtos.healths.ResponseHealthStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/healths")
public interface HealthController {
    @GetMapping("/{id}")
    ResponseEntity<ResponseHealthStatusDTO> getStatusEndpoint(@PathVariable Long id);
}
