package com.ecommerce.user.application.dto;

import lombok.Data;

@Data
public class UserUpdateRequestDto {

    private String phone;
    private String postalCode;
    private String state;
    private String city;
    private String address;
    private String profilePicture;

}
