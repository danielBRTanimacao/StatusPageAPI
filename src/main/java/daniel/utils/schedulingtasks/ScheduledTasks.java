package daniel.utils.schedulingtasks;

import daniel.repositories.UptimeRepository;
import daniel.utils.EndpointHealthHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final UptimeRepository repository;
    private final EndpointHealthHandler handler;

    @Scheduled(fixedRate = 60_000)
    public void checkUptime() {
        try {
            repository.findAll().forEach(handler::checkEndpoint);

        } catch (InvalidDataAccessResourceUsageException e) {
            log.error("Invalid access data db maybe not created");
        }
        log.info("checkup all uptime services");
    }
}
