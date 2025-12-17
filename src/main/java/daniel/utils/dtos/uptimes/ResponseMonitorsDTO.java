package daniel.utils.dtos.uptimes;

import java.time.LocalDateTime;

public record ResponseMonitorsDTO(
        Long id,
        String name,
        String url,
        boolean online,
        double ping,
        LocalDateTime lastChecked
) {
}
