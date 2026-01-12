package daniel.utils.dtos.users;

public record RequestUserDTO(
    String name,
    String email,
    String password
) {
}
