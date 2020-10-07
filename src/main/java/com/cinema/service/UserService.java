package com.cinema.service;

import com.cinema.exception.AuthenticationException;
import com.cinema.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user) throws AuthenticationException;

    Optional<User> findByEmail(String email);
}
