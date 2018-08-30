package ru.bogdanium.webstore.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.bogdanium.webstore.model.Address;
import ru.bogdanium.webstore.model.Customer;
import ru.bogdanium.webstore.model.Order;
import ru.bogdanium.webstore.model.ShippingDetail;
import ru.bogdanium.webstore.service.CartService;

import java.util.HashMap;
import java.util.Map;

/**
 * Denis, 29.08.2018
 */
@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private static final String SQL_SAVE_SHIPPING_DETAIL = "" +
            "INSERT INTO shipping_detail (name, shipping_date, shipping_address_id) " +
            "VALUES (:name, :shipping_date, :shipping_address_id)";

    private static final String SQL_SAVE_CUSTOMER = "" +
            "INSERT INTO customer (name, phone_number, billing_address_id) " +
            "VALUES (:name, :phone_number, :billing_address_id)";

    private static final String SQL_SAVE_ADDRESS = "" +
            "INSERT INTO address (door_no, street_name, area_name, state, country, zip) "
            + "VALUES (:door_no, :street_name, :area_name, :state, :country, :zip)";

    private static final String SQL_SAVE_ORDER = "" +
            "INSERT INTO orders (cart_id, customer_id, shipping_detail_id) "
            + "VALUES (:cart_id, :customer_id, :shipping_detail_id)";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CartService cartService;

    @Override
    public long saveOrder(Order order) {

        Long customerId = saveCustomer(order.getCustomer());
        Long shippingDetailId = saveShippingDetail(order.getShippingDetail());
        order.getCustomer().setCustomerId(customerId);
        order.getShippingDetail().setId(shippingDetailId);
        long createdOrderId = createOrder(order);
        cartService.clearCart(order.getCart().getId());
        return createdOrderId;
    }

    private long saveShippingDetail(ShippingDetail shippingDetail) {

        long addressId = saveAddress(shippingDetail.getShippingAddress());
        Map<String, Object> params = new HashMap<>();
        params.put("name", shippingDetail.getName());
        params.put("shipping_date", shippingDetail.getShippingDate());
        params.put("shipping_address_id", addressId);

        SqlParameterSource parameterSource = new MapSqlParameterSource(params);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_SAVE_SHIPPING_DETAIL, parameterSource, keyHolder, new String[]{"ID"});

        return keyHolder.getKey().longValue();
    }

    private long saveCustomer(Customer customer) {

        long addressId = saveAddress(customer.getBillingAddress());

        Map<String, Object> params = new HashMap<>();
        params.put("name", customer.getName());
        params.put("phone_number", customer.getPhoneNumber());
        params.put("billing_address_id", addressId);

        SqlParameterSource paramSource = new MapSqlParameterSource(params);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_SAVE_CUSTOMER, paramSource, keyHolder, new String[]{"ID"});

        return keyHolder.getKey().longValue();
    }

    private long saveAddress(Address address) {

        Map<String, Object> params = new HashMap<>();
        params.put("door_no", address.getDoorNo());
        params.put("street_name", address.getStreetName());
        params.put("area_name", address.getAreaName());
        params.put("state", address.getState());
        params.put("country", address.getCountry());
        params.put("zip", address.getZipCode());

        SqlParameterSource paramSource = new MapSqlParameterSource(params);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_SAVE_ADDRESS, paramSource, keyHolder, new String[]{"ID"});

        return keyHolder.getKey().longValue();
    }

    private long createOrder(Order order) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", order.getOrderId());
        params.put("cart_id", order.getCart().getId());
        params.put("customer_id", order.getCustomer().getCustomerId());
        params.put("shipping_detail_id", order.getShippingDetail().getId());

        SqlParameterSource paramSource = new MapSqlParameterSource(params);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SQL_SAVE_ORDER, paramSource, keyHolder, new String[]{"ID"});

        return keyHolder.getKey().longValue();
    }
}
