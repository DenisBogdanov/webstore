package ru.bogdanium.webstore.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Denis, 29.08.2018
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = -8408240615997501104L;

    private Long customerId;
    private String name;
    private Address billingAddress;
    private String phoneNumber;

    public Customer() {
        this.billingAddress = new Address();
    }

    public Customer(Long customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId);
    }
}
