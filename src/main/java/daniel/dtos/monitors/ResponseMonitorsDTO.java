package daniel.dtos.monitors;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseMonitorsDTO(
        Long id,
        String name,
        String url,
        boolean online,
        double ping,
        LocalDateTime lastChecked
) {
}
