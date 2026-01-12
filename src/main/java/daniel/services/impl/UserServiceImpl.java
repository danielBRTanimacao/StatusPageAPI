package daniel.services.impl;

import daniel.entities.UserEntity;
import daniel.repositories.UserRepository;
import daniel.services.UserService;
import daniel.utils.dtos.users.ResponseToken;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public void registerUser(UserEntity data) {

    }

    @Override
    public ResponseToken loginUser(UserEntity data) {
        log.info("Login attempt: {}", data.getName());
        UserEntity adminEntity = repository.findByName(data.getName())
                .orElseThrow(() -> {
                    log.error("User not found: {}", data.getEmail());
                    return new EntityNotFoundException("User with email " + data.getEmail() + " not found");
                });

        if (!this.passwordEncoder.matches(data.getPassword(), adminEntity.getPassword())) {
            log.error("Incorrect password for user {}", adminEntity.getEmail());
            throw new IllegalArgumentException("Invalid email or password");
        }

        repository.save(adminEntity);
        log.info("User {} successfully verified.", adminEntity.getEmail());

        return new ResponseToken(token);
    }
}
