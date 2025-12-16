package daniel.controllers.impl;

import daniel.controllers.UptimeController;
import daniel.dtos.monitors.RequestMonitorsDTO;
import daniel.dtos.monitors.ResponseMonitorsDTO;
import daniel.dtos.monitors.UpdateMonitorsDTO;
import daniel.entities.UptimeEntity;
import daniel.mappers.UptimeMapper;
import daniel.services.UptimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UptimeControllerImpl implements UptimeController {
    private final UptimeService service;
    private final UptimeMapper mapper;

    @Override
    public ResponseEntity<Page<ResponseMonitorsDTO>> listAllServicesStatus(int pgNum, int pgSize) {
        return ResponseEntity.ok().body(service.paginateAllStatus(pgNum, pgSize));
    }

    @Override
    public ResponseEntity<Void> addNewService(RequestMonitorsDTO data) {
        UptimeEntity entity = mapper.toEntity(data);
        service.createMonitor(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateSpecificService(UpdateMonitorsDTO data, Long id) {
        UptimeEntity entityUpdt = mapper.toUpdateEntity(data);
        service.updtMonitor(entityUpdt, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteServiceById(Long id) {
        service.delById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
