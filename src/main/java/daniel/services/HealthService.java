package daniel.services;

import daniel.dtos.healths.ResponseHealthStatusDTO;

public interface HealthService {
    ResponseHealthStatusDTO getStatusById(Long id);
}
