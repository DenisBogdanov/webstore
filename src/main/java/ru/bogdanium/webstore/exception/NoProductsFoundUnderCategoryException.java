package ru.bogdanium.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Denis, 25.08.2018
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No products found under this category")
public class NoProductsFoundUnderCategoryException extends RuntimeException {


}
