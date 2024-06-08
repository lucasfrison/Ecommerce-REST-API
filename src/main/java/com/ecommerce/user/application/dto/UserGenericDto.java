package com.ecommerce.user.application.dto;

import com.ecommerce.user.domain.entity.ProfileType;
import lombok.Data;

@Data
public class UserGenericDto {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String profilePicture;
    private ProfileType profileType;

}
