package com.msb.embeddedbanking.service;

import com.msb.embeddedbanking.repository.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);
}
