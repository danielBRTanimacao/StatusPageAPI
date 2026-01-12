package daniel.utils.dtos.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUserLoginDTO(
        @NotBlank
        String name,
        @Size(min = 6, max = 16)
        String password
) {
}
