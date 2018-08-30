package ru.bogdanium.webstore.service;

import ru.bogdanium.webstore.dto.CartDto;
import ru.bogdanium.webstore.model.Cart;

/**
 * Denis, 27.08.2018
 */
public interface CartService {

    void create(CartDto cartDto);

    Cart findById(String id);

    void update(String id, CartDto cartDto);

    void delete(String id);

    void addItem(String cartId, String productId);

    void removeItem(String cartId, String productId);

    Cart validate(String cartId);

    void clearCart(String cartId);
}
