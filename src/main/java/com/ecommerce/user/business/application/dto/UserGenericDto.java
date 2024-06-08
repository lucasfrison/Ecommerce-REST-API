package com.ecommerce.user.business.application.dto;

import com.ecommerce.user.business.domain.entity.ProfileType;
import lombok.Data;

@Data
public class UserGenericDto {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String postalCode;
    private String state;
    private String city;
    private String address;
    private String profilePicture;
    private ProfileType profileType;

}
