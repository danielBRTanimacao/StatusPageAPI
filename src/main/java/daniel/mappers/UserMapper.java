package daniel.mappers;

import daniel.entities.UserEntity;
import daniel.utils.dtos.users.RequestUserDTO;
import daniel.utils.dtos.users.RequestUserLoginDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toRegisterEntity(RequestUserDTO dto);

    UserEntity toLoginEntity(RequestUserLoginDTO dto);
}
