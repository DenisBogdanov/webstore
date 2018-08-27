package ru.bogdanium.webstore.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.bogdanium.webstore.dto.CartDto;
import ru.bogdanium.webstore.dto.CartItemDto;
import ru.bogdanium.webstore.model.Cart;
import ru.bogdanium.webstore.model.CartItem;
import ru.bogdanium.webstore.model.Product;
import ru.bogdanium.webstore.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Denis, 27.08.2018
 */
@Repository
public class InMemoryCartRepository implements CartRepository {

    private static final String SQL_ADD_CART = "INSERT INTO cart (id) VALUES (:id)";

    private static final String SQL_ADD_CART_ITEM = "" +
            "INSERT INTO cart_item (id, product_id, cart_id, quantity) " +
            "VALUES (:id, :product_id, :cart_id, :quantity)";

    private static final String SQL_GET_CART_BY_ID = "SELECT * FROM cart WHERE id=:id";

    private static final String SQL_UPDATE_CART_ITEM = "" +
            "UPDATE cart_item SET quantity=:quantity, product_id=:product_id " +
            "WHERE id=:id AND cart_id=:cart_id";

    private static final String SQL_DELETE_ALL_CART_ITEMS_BY_CART_ID = "" +
            "DELETE FROM cart_item " +
            "WHERE cart_id=:id";

    private static final String SQL_DELETE_CART = "DELETE FROM cart WHERE id=:id";

    private static final String SQL_UPDATE_CART_ITEM_QUANTITY = "" +
            "UPDATE cart_item SET quantity=:quantity " +
            "WHERE id=:id AND cart_id=:cart_id";

    private static final String SQL_DELETE_CART_ITEM = "" +
            "DELETE FROM cart_item " +
            "WHERE product_id=:product_id AND cart_id=:id";
    ;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ProductService productService;

    @Override
    public void create(CartDto cartDto) {
        Map<String, Object> cartParams = new HashMap<>();
        cartParams.put("id", cartDto.getId());
        jdbcTemplate.update(SQL_ADD_CART, cartParams);

        cartDto.getCartItems().stream()
                .forEach(cartItemDto -> {
                    Product product = productService.getProductById(cartItemDto.getProductId());

                    Map<String, Object> cartItemParams = new HashMap<>();
                    cartItemParams.put("id", cartItemDto.getId());
                    cartItemParams.put("product_id", product.getProductId());
                    cartItemParams.put("cart_id", cartDto.getId());
                    cartItemParams.put("quantity", cartItemDto.getQuantity());

                    jdbcTemplate.update(SQL_ADD_CART_ITEM, cartItemParams);
                });
    }

    @Override
    public Cart findById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        CartMapper cartMapper = new CartMapper(jdbcTemplate, productService);

        try {
            return jdbcTemplate.queryForObject(SQL_GET_CART_BY_ID, params, cartMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(String id, CartDto cartDto) {
        List<CartItemDto> cartItems = cartDto.getCartItems();

        cartItems.forEach(cartItem -> {
            Map<String, Object> params = new HashMap<>();
            params.put("id", cartItem.getId());
            params.put("quantity", cartItem.getQuantity());
            params.put("productId", cartItem.getProductId());
            params.put("cart_id", id);

            jdbcTemplate.update(SQL_UPDATE_CART_ITEM, params);
        });
    }

    @Override
    public void delete(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(SQL_DELETE_ALL_CART_ITEMS_BY_CART_ID, params);
        jdbcTemplate.update(SQL_DELETE_CART, params);
    }

    @Override
    public void addItem(String cartId, String productId) {
        Cart cart = null;
        String sql = null;
        cart = findById(cartId);

        if (cart == null) {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setId(cartId + productId);
            cartItemDto.setProductId(productId);
            cartItemDto.setQuantity(1);

            CartDto cartDto = new CartDto(cartId);
            cartDto.addCartItem(cartItemDto);
            create(cartDto);

        } else {
            Map<String, Object> cartItemParams = new HashMap<>();

            if (cart.getItemByProductId(productId) == null) {
                sql = SQL_ADD_CART_ITEM;
                cartItemParams.put("id", cartId + productId);
                cartItemParams.put("quantity", 1);

            } else {
                sql = SQL_UPDATE_CART_ITEM_QUANTITY;
                CartItem cartItem = cart.getItemByProductId(productId);
                cartItemParams.put("id", cartItem.getId());
                cartItemParams.put("quantity", cartItem.getQuantity() + 1);
            }

            cartItemParams.put("productId", productId);
            cartItemParams.put("cartId", cartId);

            jdbcTemplate.update(sql, cartItemParams);
        }
    }

    @Override
    public void removeItem(String cartId, String productId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", cartId);
        params.put("product_id", productId);

        jdbcTemplate.update(SQL_DELETE_CART_ITEM, params);
    }
}
