package daniel.services;

import daniel.entities.ServiceStatusEntity;

import java.util.List;

public interface ServiceStatus {
    List<ServiceStatusEntity> listAll();
}
