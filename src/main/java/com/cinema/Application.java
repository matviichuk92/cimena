package com.cinema;

import com.cinema.exception.AuthenticationException;
import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

public class Application {
    public static final Logger logger = Logger.getLogger(Application.class);
    private static final Injector injector = Injector.getInstance("com.cinema");
    private static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService sessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static final ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static final OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);
    private static final AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movie.setDescription("16+");
        movieService.add(movie);

        Movie comedy = new Movie();
        comedy.setTitle("Comedy");
        comedy.setDescription("16+");
        movieService.add(comedy);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("red");
        cinemaHallService.add(cinemaHall);

        MovieSession session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(movie);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);

        MovieSession secondSession = new MovieSession();
        secondSession.setCinemaHall(cinemaHall);
        secondSession.setMovie(comedy);
        secondSession.setShowTime(LocalDateTime.now().plusHours(3L));
        sessionService.add(secondSession);

        User roma = new User();
        roma.setEmail("matviichuk.office@gmail.com");
        roma.setPassword("1234");

        authenticationService.register(roma.getEmail(), roma.getPassword());
        User romaFromDB = authenticationService.login(roma.getEmail(), roma.getPassword());
        shoppingCartService.addSession(session, romaFromDB);
        shoppingCartService.addSession(secondSession, romaFromDB);
        orderService.completeOrder(romaFromDB, shoppingCartService
                .getByUser(romaFromDB).getTickets());
        logger.debug("Empty cart : " + shoppingCartService.getByUser(romaFromDB));
        logger.debug(orderService.getOrderHistory(romaFromDB));
    }
}
