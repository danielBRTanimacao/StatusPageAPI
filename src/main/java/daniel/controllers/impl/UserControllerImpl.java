package daniel.controllers.impl;

import daniel.controllers.UserController;
import daniel.entities.UserEntity;
import daniel.services.UserService;
import daniel.utils.dtos.users.RequestUserDTO;
import daniel.utils.dtos.users.RequestUserLoginDTO;
import daniel.utils.dtos.users.ResponseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService service;

    @Override
    public ResponseEntity<Void> createUser(RequestUserDTO data) {
        UserEntity user = new UserEntity();
        user.setName(data.name());
        user.setEmail(data.email());
        user.setPassword(data.password());
        service.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseToken> loginUser(RequestUserLoginDTO data) {
        UserEntity userData = new UserEntity();
        userData.setName(data.name());
        userData.setPassword(data.password());
        return ResponseEntity.ok().body(service.loginUser(userData));
    }
}
