package com.cinema.model.dto.mapper;

import com.cinema.model.Order;
import com.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapper {
    private final TicketDtoMapper ticketDtoMapper;

    public OrderDtoMapper(TicketDtoMapper ticketDtoMapper) {
        this.ticketDtoMapper = ticketDtoMapper;
    }

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setTickets(order.getTickets().stream()
                .map(ticketDtoMapper::mapTicketToDto)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
