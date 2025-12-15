package daniel.dtos.healths;


public record ResponseHealthStatusDTO(
        String name,
        String url,
        boolean online
) {
}
