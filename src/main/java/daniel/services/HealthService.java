package daniel.services;

import daniel.utils.dtos.healths.ResponseHealthStatusDTO;

public interface HealthService {
    ResponseHealthStatusDTO getStatusById(Long id);
}
