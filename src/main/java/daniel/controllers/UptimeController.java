package daniel.controllers;

import daniel.utils.dtos.uptimes.RequestMonitorsDTO;
import daniel.utils.dtos.uptimes.ResponseMonitorsDTO;
import daniel.utils.dtos.uptimes.UpdateMonitorsDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/endpoints")
public interface UptimeController {
    @GetMapping
    ResponseEntity<Page<ResponseMonitorsDTO>> listAllServicesStatus(
            @RequestParam(defaultValue = "0") int pgNum,
            @RequestParam(defaultValue = "25") int pgSize
    );
    @PostMapping
    ResponseEntity<Void> addNewService(@Valid @RequestBody RequestMonitorsDTO data);
    @PutMapping("/{id}")
    ResponseEntity<Void> updateSpecificService(@RequestBody UpdateMonitorsDTO data, @PathVariable Long id);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteServiceById(@PathVariable Long id);
}
