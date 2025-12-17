package daniel.utils;

import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.repositories.UptimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EndpointHealthHandler {
    private final UptimeRepository repository;
    private final WebClient webClient;

    public void updateEndpointStatus(UptimeEntity e, boolean isOn) {
        e.setPing(0);
        e.setOnline(isOn);
        e.setLastChecked(LocalDateTime.now());
        repository.save(e);
    }

    public void updateEndpointStatus(UptimeEntity e, boolean isOn, long ping) {
        e.setPing(ping);
        e.setOnline(isOn);
        e.setLastChecked(LocalDateTime.now());
        repository.save(e);
    }

    public void checkEndpoint(UptimeEntity entity) {
        long reqInstant = Instant.now().toEpochMilli();

        webClient.get()
                .uri(entity.getUrl())
                .exchangeToMono(res -> {
                    long actualPing = Instant.now().toEpochMilli() - reqInstant;

                    if (res.statusCode().value() == 999) {
                        updateEndpointStatus(entity, false);
                        throw new PermissionDeniedException("Blocked by anti-bot (999)");
                    } else if (!res.statusCode().equals(HttpStatus.OK)) {
                        updateEndpointStatus(entity, false);
                    } else {
                        updateEndpointStatus(entity, true, actualPing);
                    }
                    return Mono.just(true);
                })
                .block();
    }
}
