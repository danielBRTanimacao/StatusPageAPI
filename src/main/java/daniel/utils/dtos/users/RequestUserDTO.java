package daniel.utils.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUserDTO(
        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @Size(min = 6, max = 16)
        String password
) {
}
