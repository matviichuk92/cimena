package com.cinema.model.dto.mapper;

import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.dto.ShoppingCartDtoResponse;
import com.cinema.model.dto.TicketResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoMapper {
    private final MovieSessionDtoMapper movieSessionDtoMapper;

    public ShoppingCartDtoMapper(MovieSessionDtoMapper movieSessionDtoMapper) {
        this.movieSessionDtoMapper = movieSessionDtoMapper;
    }

    public ShoppingCartDtoResponse mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartDtoResponse cart = new ShoppingCartDtoResponse();
        cart.setId(shoppingCart.getId());
        cart.setTickets(shoppingCart.getTickets().stream()
                .map(this::mapTicketToDto)
                .collect(Collectors.toList()));
        return cart;

    }

    public TicketResponseDto mapTicketToDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setSession(movieSessionDtoMapper.mapToSession(ticket.getMovieSession()));
        return ticketResponseDto;
    }
}
