package ru.bogdanium.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogdanium.webstore.dto.CartDto;
import ru.bogdanium.webstore.exception.InvalidCartException;
import ru.bogdanium.webstore.model.Cart;
import ru.bogdanium.webstore.repository.CartRepository;

/**
 * Denis, 27.08.2018
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void create(CartDto cartDto) {
        cartRepository.create(cartDto);
    }

    @Override
    public Cart findById(String id) {
        return cartRepository.findById(id);
    }

    @Override
    public void update(String id, CartDto cartDto) {
        cartRepository.update(id, cartDto);
    }

    @Override
    public void delete(String id) {
        cartRepository.delete(id);
    }

    @Override
    public void addItem(String cartId, String productId) {
        cartRepository.addItem(cartId, productId);
    }

    @Override
    public void removeItem(String cartId, String productId) {
        cartRepository.removeItem(cartId, productId);
    }

    @Override
    public Cart validate(String cartId) {
        Cart cart = cartRepository.findById(cartId);
        if (cart == null || cart.getCartItems().size() == 0) {
            throw new InvalidCartException("Cart with id=" + cartId + " doesn't exist.");
        }
        return cart;
    }

    @Override
    public void clearCart(String cartId) {
        cartRepository.clearCart(cartId);
    }
}
