package com.cinema.controller;

import com.cinema.model.dto.OrderResponseDto;
import com.cinema.model.dto.mapper.OrderDtoMapper;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderDtoMapper orderDtoMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderDtoMapper orderDtoMapper, OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderDtoMapper = orderDtoMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        orderService.completeOrder(userService.get(userId),
                shoppingCartService.getByUser(userService.get(userId)).getTickets());
    }

    @GetMapping
    public List<OrderResponseDto> getUserOrders(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId)).stream()
                .map(orderDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
