package ru.bogdanium.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bogdanium.webstore.model.Product;

import java.math.BigDecimal;

/**
 * Denis, 26.08.2018
 */
@Component
public class UnitsInStockValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (product.getUnitPrice() != null &&
                BigDecimal.valueOf(1000).compareTo(product.getUnitPrice()) <= 0 &&
                product.getUnitsInStock() > 99) {

            errors.rejectValue("unitsInStock",
                    "ru.bogdanov.webstore.validator.UnitsInStockValidator.message");
        }
    }
}
