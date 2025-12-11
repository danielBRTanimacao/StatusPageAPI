package daniel.repositories;

import daniel.entities.MonitorDowntimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorDowntimeRepository extends JpaRepository<MonitorDowntimeEntity, Long> {
}
