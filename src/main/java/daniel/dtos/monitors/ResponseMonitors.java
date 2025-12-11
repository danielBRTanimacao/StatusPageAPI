package daniel.dtos.monitors;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseMonitors(
        String name,
        String url,
        boolean online,
        LocalDateTime lastChecked,
        List<Long> downtimes
) {
}
