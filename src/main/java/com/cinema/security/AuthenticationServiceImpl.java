package com.cinema.security;

import com.cinema.Application;
import com.cinema.exception.AuthenticationException;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.User;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent() && HashUtil.hashPassword(password, userFromDB.get().getSalt())
                .equals(userFromDB.get().getPassword())) {
            Application.logger.info(userFromDB.get());
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect email or password!");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        Application.logger.info(user);
        return user;
    }
}
