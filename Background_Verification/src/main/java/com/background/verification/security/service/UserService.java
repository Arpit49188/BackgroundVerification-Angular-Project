package com.background.verification.security.service;

import com.background.verification.security.model.User;
import com.background.verification.security.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
