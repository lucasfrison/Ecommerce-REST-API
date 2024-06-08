package com.ecommerce.user.business.application.dto;

import com.ecommerce.user.business.domain.entity.ProfileType;
import lombok.Data;

@Data
public class UserPersistRequestDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String postalCode;
    private String state;
    private String city;
    private String address;
    private String profilePicture;
    private ProfileType profileType;

}
