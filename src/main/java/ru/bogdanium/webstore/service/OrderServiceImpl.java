package ru.bogdanium.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bogdanium.webstore.model.Order;
import ru.bogdanium.webstore.repository.OrderRepository;

/**
 * Denis, 30.08.2018
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Long saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    }
}
