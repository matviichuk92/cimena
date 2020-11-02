package com.cinema.controller;

import com.cinema.model.dto.ShoppingCartDtoResponse;
import com.cinema.model.dto.mapper.ShoppingCartDtoMapper;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ShoppingCartDtoMapper shoppingCartDtoMapper,
                                  UserService userService,
                                  MovieSessionService movieSessionService) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSessionToCart(@RequestParam Long sessionId,
                                      Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        shoppingCartService.addSession(movieSessionService.get(sessionId),
                userService.findByEmail(userDetails.getUsername()).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartDtoResponse getByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return shoppingCartDtoMapper.mapToDto(shoppingCartService
                .getByUser(userService.findByEmail(userDetails.getUsername()).get()));
    }
}
