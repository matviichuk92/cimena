package com.cinema.service;

import com.cinema.model.Order;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(User user, List<Ticket> tickets);

    List<Order> getOrderHistory(User user);
}
