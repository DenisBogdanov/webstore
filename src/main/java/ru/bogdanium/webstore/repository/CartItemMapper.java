package ru.bogdanium.webstore.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.bogdanium.webstore.model.CartItem;
import ru.bogdanium.webstore.service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Denis, 27.08.2018
 */
public class CartItemMapper implements RowMapper<CartItem> {

    private ProductService productService;

    public CartItemMapper(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartItem cartItem = new CartItem(rs.getString("id"));
        cartItem.setProduct(productService.getProductById(rs.getString("product_id")));
        cartItem.setQuantity(rs.getInt("quantity"));

        return cartItem;
    }
}
