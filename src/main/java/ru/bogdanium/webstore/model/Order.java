package ru.bogdanium.webstore.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Denis, 29.08.2018
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -6482892897683353129L;

    private Long orderId;
    private Cart cart;
    private Customer customer;
    private ShippingDetail shippingDetail;

    public Order() {
        this.customer = new Customer();
        this.shippingDetail = new ShippingDetail();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShippingDetail getShippingDetail() {
        return shippingDetail;
    }

    public void setShippingDetail(ShippingDetail shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId);
    }
}
