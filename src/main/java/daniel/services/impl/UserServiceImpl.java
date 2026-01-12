package daniel.services.impl;

import daniel.entities.UserEntity;
import daniel.repositories.UserRepository;
import daniel.services.TokenService;
import daniel.services.UserService;
import daniel.utils.dtos.users.ResponseToken;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserEntity data) {

    }

    @Override
    public ResponseToken loginUser(UserEntity data) {
        log.info("Login attempt: {}", data.getName());
        UserEntity user = repository.findByName(data.getName())
                .orElseThrow(() -> {
                    log.error("User not found: {}", data.getName());
                    return new EntityNotFoundException("User with email " + data.getName() + " not found");
                });

        if (!this.passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            log.error("Incorrect password for user {}", user.getName());
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = tokenService.generateToken(user);
        log.info("User {} successfully verified.", user.getName());

        return new ResponseToken(token);
    }
}
