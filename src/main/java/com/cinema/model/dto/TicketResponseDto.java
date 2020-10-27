package com.cinema.model.dto;

public class TicketResponseDto {
    private Long id;
    private MovieSessionResponseDto session;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieSessionResponseDto getSession() {
        return session;
    }

    public void setSession(MovieSessionResponseDto session) {
        this.session = session;
    }
}
