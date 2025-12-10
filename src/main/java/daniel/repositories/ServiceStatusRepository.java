package daniel.repositories;

import daniel.entities.ServiceStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceStatusRepository extends JpaRepository<ServiceStatusEntity, Long> {
}
