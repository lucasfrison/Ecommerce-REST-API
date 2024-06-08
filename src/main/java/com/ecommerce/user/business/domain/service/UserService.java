package com.ecommerce.user.business.domain.service;

import com.ecommerce.user.business.domain.entity.User;
import com.ecommerce.user.business.domain.exception.UserBusinessException;

import java.util.List;

public interface UserService {

    User getById(String id);
    User getByName(String name);
    User getByEmail(String email);
    List<User> getAll();
    String save(User user) throws UserBusinessException;
    void merge(User user);
    User login(String email, String password) throws UserBusinessException;

}
