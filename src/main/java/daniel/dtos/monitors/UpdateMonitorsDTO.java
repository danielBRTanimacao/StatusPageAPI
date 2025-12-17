package daniel.dtos.monitors;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public record UpdateMonitorsDTO(
        String name,
        @URL
        String url,
        boolean online,
        @DecimalMin(value = "0.0", inclusive = true, message = "ping is zero or a positive value")
        double ping,
        @PastOrPresent
        LocalDateTime lastChecked
) {
}
