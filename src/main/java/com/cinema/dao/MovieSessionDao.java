package com.cinema.dao;

import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    List<MovieSession> getAvailableSessions(Long movieId, LocalDate showTime);

    MovieSession getById(Long id);
}
