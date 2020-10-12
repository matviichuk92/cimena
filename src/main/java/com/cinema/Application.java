package com.cinema;

import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.User;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.time.LocalDateTime;

public class Application {
    private static final Injector injector = Injector.getInstance("com.cinema");
    static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    static final CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    static final MovieSessionService sessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    static final UserService userService
            = (UserService) injector.getInstance(UserService.class);
    static final ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    static final OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) {
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
        userService.add(roma);

        User mila = new User();
        mila.setEmail("mila.office@gmail.com");
        mila.setPassword("1234");
        userService.add(mila);

        shoppingCartService.registerNewShoppingCart(roma);
        shoppingCartService.addSession(session,roma);
        shoppingCartService.addSession(secondSession,roma);
        System.out.println(shoppingCartService.getByUser(roma));

        orderService.completeOrder(roma, shoppingCartService.getByUser(roma).getTickets());
        System.out.println("Empty cart : " + shoppingCartService.getByUser(roma));
        System.out.println(orderService.getOrderHistory(roma));
    }
}
