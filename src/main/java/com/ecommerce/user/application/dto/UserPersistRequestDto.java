package com.ecommerce.user.application.dto;

import com.ecommerce.user.domain.entity.ProfileType;
import lombok.Data;

@Data
public class UserPersistRequestDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String profilePicture;
    private ProfileType profileType;

}
