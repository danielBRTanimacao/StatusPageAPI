package daniel.utils.schedulingtasks;

import daniel.repositories.UptimeRepository;
import daniel.utils.EndpointHealthHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final UptimeRepository repository;
    private final EndpointHealthHandler handler;

    @Scheduled(fixedRate = 5000)
    public void checkUptime() {
        repository.findAll().forEach(handler::checkEndpoint);
        log.info("checkup all uptime services");
    }
}
