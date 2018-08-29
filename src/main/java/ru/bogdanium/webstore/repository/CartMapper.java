package ru.bogdanium.webstore.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.bogdanium.webstore.model.Cart;
import ru.bogdanium.webstore.model.CartItem;
import ru.bogdanium.webstore.service.ProductService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Denis, 27.08.2018
 */
public class CartMapper implements RowMapper<Cart> {

    private static final String SQL_GET_CART_ITEMS_BY_ID = "" +
            "SELECT * FROM cart_item " +
            "WHERE cart_id=:cart_id";

    private CartItemMapper cartItemMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public CartMapper(NamedParameterJdbcTemplate jdbcTemplate, ProductService productService) {
        this.jdbcTemplate = jdbcTemplate;
        cartItemMapper = new CartItemMapper(productService);
    }


    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("id");
        Cart cart = new Cart(id);
        Map<String, Object> params = new HashMap<>();
        params.put("cart_id", id);
        List<CartItem> cartItems = jdbcTemplate.query(SQL_GET_CART_ITEMS_BY_ID, params, cartItemMapper);
        cart.setCartItems(cartItems);
        return cart;
    }
}
