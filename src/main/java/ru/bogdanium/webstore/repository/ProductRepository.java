package ru.bogdanium.webstore.repository;

import ru.bogdanium.webstore.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Denis, 19.08.2018
 */
public interface ProductRepository {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    Product getProductById(String productId);

    void updateStock(String productId, long qtyOfUnits);
}
