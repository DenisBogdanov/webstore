package ru.bogdanium.webstore.repository;

import ru.bogdanium.webstore.model.Product;

import java.util.List;

/**
 * Denis, 19.08.2018
 */
public interface ProductRepository {
    List<Product> getAllProducts();
}
