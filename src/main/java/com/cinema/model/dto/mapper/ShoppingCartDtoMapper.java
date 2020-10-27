package com.cinema.model.dto.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.dto.ShoppingCartDtoResponse;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {

    public ShoppingCartDtoResponse mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartDtoResponse cart = new ShoppingCartDtoResponse();
        cart.setId(shoppingCart.getId());
        cart.setTicketsId(shoppingCart.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        return cart;
    }
}
