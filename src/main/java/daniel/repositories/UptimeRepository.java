package daniel.repositories;

import daniel.entities.UptimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UptimeRepository extends JpaRepository<UptimeEntity, Long> {
}
