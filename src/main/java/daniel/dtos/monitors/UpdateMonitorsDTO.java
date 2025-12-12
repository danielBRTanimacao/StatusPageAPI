package daniel.dtos.monitors;

import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateMonitorsDTO(
        String name,
        @URL
        String url,
        boolean online,
        LocalDateTime lastChecked,
        List<Long> downtimes
) {
}
