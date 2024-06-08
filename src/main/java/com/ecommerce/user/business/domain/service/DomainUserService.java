package com.ecommerce.user.business.domain.service;

import com.ecommerce.user.business.domain.entity.User;
import com.ecommerce.user.business.domain.exception.UserBusinessException;
import com.ecommerce.user.business.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DomainUserService implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public User getById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userRepository.getByName(name);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public String save(User user) throws UserBusinessException {
        if (getByEmail(user.getEmail()) != null) {
            throw new UserBusinessException(UserBusinessException.USER_EMAIL_ALEADY_EXISTS);
        }
        return userRepository.save(user);
    }

    @Override
    public void merge(User user) {
        userRepository.merge(user);
    }

    @Override
    public User login(String email, String password) throws UserBusinessException {
        User user = userRepository.login(email, password);
        if (userRepository.login(email, password) == null) {
            throw new UserBusinessException(UserBusinessException.INVALID_USER_EMAIL_OR_PASSWORD);
        }
        return user;
    }
}
