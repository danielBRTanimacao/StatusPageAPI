package daniel.services.impl;

import daniel.entities.ServiceStatusEntity;
import daniel.repositories.ServiceStatusRepository;
import daniel.services.ServiceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceStatusImpl implements ServiceStatus {
    private final ServiceStatusRepository repository;

    @Override
    public List<ServiceStatusEntity> listAll() {
        return repository.findAll();
    }
}
