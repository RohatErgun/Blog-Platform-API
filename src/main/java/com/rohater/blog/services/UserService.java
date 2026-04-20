package com.rohater.blog.services;

import com.rohater.blog.domain.model.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
