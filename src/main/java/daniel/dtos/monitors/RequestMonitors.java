package daniel.dtos.monitors;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record RequestMonitors(
        @NotBlank
        String name,
        @NotBlank
        @URL
        String url
) {
}
