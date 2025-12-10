package daniel.repositories;

import daniel.entities.MonitorStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorStatusRepository extends JpaRepository<MonitorStatusEntity, Long> {
}
