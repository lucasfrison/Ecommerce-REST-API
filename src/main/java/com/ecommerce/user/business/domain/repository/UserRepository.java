package com.ecommerce.user.business.domain.repository;

import com.ecommerce.user.business.domain.entity.User;

import java.util.List;

public interface UserRepository {

    User getById(String id);
    User getByName(String name);
    User getByEmail(String email);
    List<User> getAll();
    String save(User product);
    void merge(User product);
    User login(String email, String password);

}
