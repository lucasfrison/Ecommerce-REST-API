package com.ecommerce.user.application.mapper;

import com.ecommerce.user.application.dto.UserGenericDto;
import com.ecommerce.user.application.dto.UserPersistRequestDto;
import com.ecommerce.user.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface UserMapper  {

    UserGenericDto userToUserDto(User user);
    User userPostRequestDtoToUser(UserPersistRequestDto userPersistRequestDto);

}
