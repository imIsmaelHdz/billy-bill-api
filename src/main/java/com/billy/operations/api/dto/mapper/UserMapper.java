package com.billy.operations.api.dto.mapper;

import com.billy.operations.api.dto.UserDto;
import com.billy.operations.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "regimes", source = "user.regimes")
    UserDto userToUserDto(User user);
}
