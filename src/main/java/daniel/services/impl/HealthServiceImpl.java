package daniel.services.impl;

import daniel.config.WebClientConfig;
import daniel.dtos.healths.ResponseHealthStatusDTO;
import daniel.entities.UptimeEntity;
import daniel.exceptions.customs.NotFoundException;
import daniel.exceptions.customs.PermissionDeniedException;
import daniel.mappers.HealthMapper;
import daniel.repositories.UptimeRepository;
import daniel.services.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final UptimeRepository repository;
    private final WebClient webClient;
    private final HealthMapper mapper;

    @Override
    public ResponseHealthStatusDTO getStatusById(Long id) {
        UptimeEntity entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Entity with id " + id + " Not found")
        );


        try {
            webClient.get()
                    .uri(entity.getUrl())
                    .exchangeToMono(res -> {
                        if (res.statusCode().value() == 999) {
                            throw new PermissionDeniedException("Blocked by anti-bot (999)");
                        }
                        return Mono.just(true);
                    })
                    .block();
            updateEndpointStatus(entity, true);
        } catch (Exception e) {
            updateEndpointStatus(entity, false);
        }

        return mapper.toDTO(entity);
    }

    public void updateEndpointStatus(UptimeEntity e, boolean isOn) {
        e.setPing(isOn ? 100 : 0);
        e.setOnline(isOn);
        e.setLastChecked(LocalDateTime.now());
        repository.save(e);
    }
}
