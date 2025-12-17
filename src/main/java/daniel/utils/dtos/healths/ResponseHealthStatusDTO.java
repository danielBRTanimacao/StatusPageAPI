package daniel.utils.dtos.healths;


public record ResponseHealthStatusDTO(
        String name,
        String url,
        boolean online,
        double ping
) {
}
