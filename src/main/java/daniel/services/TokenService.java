package daniel.services;

import daniel.entities.UserEntity;

public interface TokenService {
    String generateToken(UserEntity user);
    String validateToken(String token);
}
