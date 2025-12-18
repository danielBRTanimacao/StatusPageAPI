package daniel.utils;

import daniel.entities.UptimeEntity;
import daniel.repositories.UptimeRepository;
import lombok.RequiredArgsConstructor;
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
        long start = Instant.now().toEpochMilli();

        try {
            webClient.get()
                    .uri(entity.getUrl())
                    .exchangeToMono(res -> {
                        long ping = Instant.now().toEpochMilli() - start;

                        if (res.statusCode().value() == 999) {
                            updateEndpointStatus(entity, false);
                        } else if (res.statusCode().is2xxSuccessful()
                                || res.statusCode().is3xxRedirection()) {
                            updateEndpointStatus(entity, true, ping);
                        } else {
                            updateEndpointStatus(entity, false);
                        }

                        return Mono.empty();
                    })
                    .block();
        } catch (Exception ex) {
            updateEndpointStatus(entity, false);
        }
    }
}
