package com.ecommerce.user.domain.entity;

import lombok.Data;

@Data
public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String profilePicture;
    private ProfileType profileType;

}
