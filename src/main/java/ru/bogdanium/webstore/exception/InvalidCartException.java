package ru.bogdanium.webstore.exception;

/**
 * Denis, 29.08.2018
 */
public class InvalidCartException extends RuntimeException {

    public InvalidCartException(String message) {
        super(message);
    }
}
