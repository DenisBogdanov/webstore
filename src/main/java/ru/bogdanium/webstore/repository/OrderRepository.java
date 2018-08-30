package ru.bogdanium.webstore.repository;

import ru.bogdanium.webstore.model.Order;

/**
 * Denis, 29.08.2018
 */
public interface OrderRepository {

    long saveOrder(Order order);
}
