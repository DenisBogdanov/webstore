package ru.bogdanium.webstore.service;

import ru.bogdanium.webstore.model.Product;

import java.util.List;

/**
 * Denis, 19.08.2018
 */
public interface ProductService {
    List<Product> getAllProducts();

    void updateAllStock();
}
