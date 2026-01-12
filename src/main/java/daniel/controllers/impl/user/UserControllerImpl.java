package daniel.controllers.impl.user;

import daniel.controllers.user.UserController;
import daniel.entities.UserEntity;
import daniel.mappers.UserMapper;
import daniel.services.user.UserService;
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
    private final UserMapper mapper;

    @Override
    public ResponseEntity<Void> createUser(RequestUserDTO data) {
        UserEntity user = mapper.toRegisterEntity(data);
        service.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseToken> loginUser(RequestUserLoginDTO data) {
        UserEntity userData = mapper.toLoginEntity(data);
        return ResponseEntity.ok().body(service.loginUser(userData));
    }
}
