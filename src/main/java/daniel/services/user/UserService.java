package daniel.services.user;

import daniel.entities.UserEntity;
import daniel.utils.dtos.users.ResponseToken;

public interface UserService {
    void registerUser(UserEntity data);
    ResponseToken loginUser(UserEntity data);
}
