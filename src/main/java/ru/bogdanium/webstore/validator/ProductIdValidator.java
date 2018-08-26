package ru.bogdanium.webstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bogdanium.webstore.exception.ProductNotFoundException;
import ru.bogdanium.webstore.model.Product;
import ru.bogdanium.webstore.service.ProductService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Denis, 26.08.2018
 */
@Component
public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

    @Autowired
    private ProductService productService;

    @Override
    public void initialize(ProductId productId) {
        // intentionally left blank; this is the place to initialize the constraint
        // annotation for any sensible default values
    }

    @Override
    public boolean isValid(String productId, ConstraintValidatorContext context) {

        Product product;

        try {
            product = productService.getProductById(productId);
        } catch (ProductNotFoundException e) {
            return true;
        }

        if (product != null) {
            return false;
        }

        return false;
    }
}
