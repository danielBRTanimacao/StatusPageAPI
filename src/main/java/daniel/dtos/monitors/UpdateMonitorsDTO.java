package daniel.dtos.monitors;

import jakarta.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.URL;

public record UpdateMonitorsDTO(
        String name,
        @URL
        String url,
        boolean online,
        @DecimalMin(value = "0.0", inclusive = true, message = "ping is zero or a positive value")
        double ping
) {
}
