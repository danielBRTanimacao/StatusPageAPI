package daniel.utils.dtos.uptimes;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record RequestMonitorsDTO(
        @NotBlank
        String name,
        @NotBlank
        @URL
        String url
) {
}
