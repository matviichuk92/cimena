package com.cinema.model.dto;

import java.util.List;

public class ShoppingCartDtoResponse {
    private Long id;
    private List<TicketResponseDto> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }
}
