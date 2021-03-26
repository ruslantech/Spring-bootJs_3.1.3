package com.ruslantech.springbootjs.service;

import com.ruslantech.springbootjs.model.User;

import java.util.Set;

public interface UserService {

    void add(User user, String[] rolesId);

    Set<User> listUsers();

    void remove(Long id);

    void update(User user, String[] rolesName);

    User getUserById(Long id);

    User getUserByFirstName(String name);

    boolean checkUserById(Long id);
}
