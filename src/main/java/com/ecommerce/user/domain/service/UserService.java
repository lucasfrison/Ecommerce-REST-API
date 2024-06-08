package com.ecommerce.user.domain.service;

import com.ecommerce.user.domain.entity.User;

import java.util.List;

public interface UserService {

    User getById(String id);
    User getByName(String name);
    User getByEmail(String email);
    List<User> getAll();
    String save(User user);
    void merge(User user);

}
