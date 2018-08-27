package ru.bogdanium.webstore.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Denis, 27.08.2018
 */
public class Cart {
    private String id;
    private List<CartItem> cartItems;
    private BigDecimal grandTotal;

    public Cart(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getGrandTotal() {
        updateGrandTotal();
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartItem getItemByProductId(String productId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getProductId().equals(productId))
                .findAny()
                .orElse(null);
    }

    public void updateGrandTotal() {
        Function<CartItem, BigDecimal> totalMapper = CartItem::getTotalPrice;

        BigDecimal grandTotal = cartItems.stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.setGrandTotal(grandTotal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
