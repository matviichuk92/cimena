package com.cinema.model.dto.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.dto.ShoppingCartDtoResponse;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {
    private final TicketDtoMapper ticketDtoMapper;

    public ShoppingCartDtoMapper(TicketDtoMapper ticketDtoMapper) {
        this.ticketDtoMapper = ticketDtoMapper;
    }

    public ShoppingCartDtoResponse mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartDtoResponse cart = new ShoppingCartDtoResponse();
        cart.setId(shoppingCart.getId());
        cart.setTickets(shoppingCart.getTickets().stream()
                .map(ticketDtoMapper::mapTicketToDto)
                .collect(Collectors.toList()));
        return cart;
    }
}
