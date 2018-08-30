package ru.bogdanium.webstore.service;

import ru.bogdanium.webstore.model.Order;

/**
 * Denis, 30.08.2018
 */
public interface OrderService {

    Long saveOrder(Order order);
}
