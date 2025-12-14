package daniel.dtos.healths;

import java.time.LocalDateTime;

public record ResponseHealthStatusDTO(
        String name,
        String url,
        boolean online,
        LocalDateTime lastChecked
) {
}
