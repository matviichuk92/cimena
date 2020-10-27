package com.cinema.controller;

import com.cinema.model.dto.ShoppingCartDtoResponse;
import com.cinema.model.dto.mapper.ShoppingCartDtoMapper;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
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
                                      @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.get(sessionId), userService.get(userId));
    }

    @GetMapping("/by-user")
    public ShoppingCartDtoResponse getByUser(@RequestParam Long userId) {
        return shoppingCartDtoMapper.mapToDto(shoppingCartService
                .getByUser(userService.get(userId)));
    }
}
