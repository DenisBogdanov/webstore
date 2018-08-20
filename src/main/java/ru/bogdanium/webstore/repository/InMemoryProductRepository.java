package ru.bogdanium.webstore.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.bogdanium.webstore.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Denis, 19.08.2018
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {

    private static final String SQL_SELECT_ALL = "SELECT * FROM products";

    private static final String SQL_UPDATE_STOCK = "" +
            "UPDATE products " +
            "SET units_in_stock=:unitsInStock " +
            "WHERE id=:id";

    private static final String SQL_SELECT_BY_CATEGORY = "" +
            "SELECT * FROM products " +
            "WHERE category=:category";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        Map<String, Object> params = new HashMap<>();
        return jdbcTemplate.query(SQL_SELECT_ALL, params, new ProductMapper());
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        return jdbcTemplate.query(SQL_SELECT_BY_CATEGORY, params, new ProductMapper());
    }

    @Override
    public void updateStock(String productId, long qtyOfUnits) {
        Map<String, Object> params = new HashMap<>();
        params.put("unitsInStock", qtyOfUnits);
        params.put("id", productId);
        jdbcTemplate.update(SQL_UPDATE_STOCK, params);
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();

            product.setProductId(rs.getString("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setUnitPrice(rs.getBigDecimal("unit_price"));
            product.setManufacturer(rs.getString("manufacturer"));
            product.setCategory(rs.getString("category"));
            product.setCondition(rs.getString("condition"));
            product.setUnitsInStock(rs.getLong("units_in_stock"));
            product.setUnitsInOrder(rs.getLong("units_in_order"));
            product.setDiscontinued(rs.getBoolean("discontinued"));

            return product;
        }
    }
}
