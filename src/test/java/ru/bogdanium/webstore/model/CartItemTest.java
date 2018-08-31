package ru.bogdanium.webstore.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Denis, 31.08.2018
 */
public class CartItemTest {

    private CartItem cartItem;

    @Before
    public void setUp() throws Exception {
        cartItem = new CartItem("1");
    }

    @Test
    public void cartItemTotalPriceShouldBeEqualToProductUnitPriceInCaseOfSingleQuantity() {
        Product iphone = new Product("P1234", "iPhone 6s", new BigDecimal(500));
        cartItem.setProduct(iphone);

        BigDecimal totalPrice = cartItem.getTotalPrice();

        assertEquals(iphone.getUnitPrice(), totalPrice);
    }
}