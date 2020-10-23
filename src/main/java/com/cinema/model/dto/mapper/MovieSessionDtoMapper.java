package com.cinema.model.dto.mapper;

import com.cinema.model.MovieSession;
import com.cinema.model.dto.MovieSessionRequestDto;
import com.cinema.model.dto.MovieSessionResponseDto;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionDtoMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionDtoMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto createSessionResponse(MovieSession movieSession) {
        MovieSessionResponseDto session = new MovieSessionResponseDto();
        session.setSessionId(movieSession.getId());
        session.setMovieId(movieSession.getMovie().getId());
        session.setShowTime(movieSession.getShowTime());
        return session;
    }

    public MovieSession createSessionRequest(MovieSessionRequestDto sessionRequestDto) {
        MovieSession session = new MovieSession();
        session.setMovie(movieService.getById(sessionRequestDto.getMovieId()));
        session.setCinemaHall(cinemaHallService.getById(sessionRequestDto.getHallId()));
        session.setShowTime(sessionRequestDto.getShowTime());
        return session;
    }
}
