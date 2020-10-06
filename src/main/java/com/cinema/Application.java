package com.cinema;

import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
    private static final Injector injector = Injector.getInstance("com.cinema");
    static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    static final CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    static final MovieSessionService sessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie.setDescription("16+");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("red");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(movie);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);
        sessionService.findAvailableSessions(movie.getId(),LocalDate.now())
                .forEach(System.out::println);

        MovieSession sessionSecond = new MovieSession();
        sessionSecond.setCinemaHall(cinemaHall);
        sessionSecond.setMovie(movie);
        sessionSecond.setShowTime(LocalDateTime.of(2020,11,1,12,0));
        sessionService.add(sessionSecond);
        sessionService.findAvailableSessions(movie.getId(),LocalDate.now())
                .forEach(System.out::println);
    }
}
