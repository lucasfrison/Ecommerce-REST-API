package com.ecommerce.user.business.application.mapper;

import com.ecommerce.user.business.application.dto.UserGenericDto;
import com.ecommerce.user.business.application.dto.UserPersistRequestDto;
import com.ecommerce.user.business.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface UserMapper  {

    UserGenericDto userToUserDto(User user);
    User userPostRequestDtoToUser(UserPersistRequestDto userPersistRequestDto);

}
