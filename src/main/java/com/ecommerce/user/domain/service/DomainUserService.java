package com.ecommerce.user.domain.service;

import com.ecommerce.user.domain.entity.User;
import com.ecommerce.user.domain.repository.UserRepository;
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
    public String save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void merge(User user) {
        userRepository.merge(user);
    }
}
