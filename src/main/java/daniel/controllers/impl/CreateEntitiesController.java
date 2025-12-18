package daniel.controllers.impl;

import daniel.entities.UptimeEntity;
import daniel.repositories.UptimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class CreateEntitiesController {
    private final UptimeRepository repo;

    @GetMapping
    public ResponseEntity<Void> createListOfEntities() {
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            boolean isTrue = random.nextBoolean();
            UptimeEntity entity = new UptimeEntity();
            entity.setOnline(false);
            entity.setPing(0);
            entity.setName("Teste " + (isTrue ? "Good" : "Bad") + " Endpoint");
            entity.setUrl("https://httpbin.org/status/" + (isTrue ? "200" : "400"));
            entity.setLastChecked(LocalDateTime.now());
            repo.save(entity);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
