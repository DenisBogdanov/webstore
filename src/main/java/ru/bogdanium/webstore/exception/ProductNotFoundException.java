package ru.bogdanium.webstore.exception;

/**
 * Denis, 25.08.2018
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
