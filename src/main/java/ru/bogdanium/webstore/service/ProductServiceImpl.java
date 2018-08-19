package ru.bogdanium.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogdanium.webstore.model.Product;
import ru.bogdanium.webstore.repository.ProductRepository;

import java.util.List;

/**
 * Denis, 19.08.2018
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public void updateAllStock() {
        List<Product> products = productRepository.getAllProducts();
        for (Product product : products) {
            if (product.getUnitsInStock() < 500) {
                productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
            }
        }
    }
}
