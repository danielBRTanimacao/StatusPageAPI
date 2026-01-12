package daniel.controllers.user;

import daniel.utils.dtos.users.RequestUserDTO;
import daniel.utils.dtos.users.RequestUserLoginDTO;
import daniel.utils.dtos.users.ResponseToken;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/users")
public interface UserController {
    @PostMapping
    ResponseEntity<Void> createUser(@Valid @RequestBody RequestUserDTO data);
    @PostMapping("/login")
    ResponseEntity<ResponseToken> loginUser(@Valid @RequestBody RequestUserLoginDTO data);
}
