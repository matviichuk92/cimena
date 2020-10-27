package com.cinema.model.dto.mapper;

import com.cinema.model.Ticket;
import com.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoMapper {
    private final MovieSessionDtoMapper movieSessionDtoMapper;

    public TicketDtoMapper(MovieSessionDtoMapper movieSessionDtoMapper) {
        this.movieSessionDtoMapper = movieSessionDtoMapper;
    }

    public TicketResponseDto mapTicketToDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setSession(movieSessionDtoMapper.mapToSession(ticket.getMovieSession()));
        return ticketResponseDto;
    }
}
