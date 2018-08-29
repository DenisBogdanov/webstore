DROP TABLE cart_item IF EXISTS;
DROP TABLE cart IF EXISTS;
DROP TABLE products IF EXISTS;

CREATE TABLE products (
  id             VARCHAR(25) PRIMARY KEY,
  name           VARCHAR(50),
  description    VARCHAR(250),
  unit_price     DECIMAL,
  manufacturer   VARCHAR(50),
  category       VARCHAR(50),
  condition      VARCHAR(50),
  units_in_stock BIGINT,
  units_in_order BIGINT,
  discontinued   BOOLEAN
);


CREATE TABLE cart (
  id VARCHAR(50) PRIMARY KEY
);


CREATE TABLE cart_item (
  id         VARCHAR(75),
  product_id VARCHAR(25) FOREIGN KEY REFERENCES products (id),
  cart_id    VARCHAR(50) FOREIGN KEY REFERENCES cart (id),
  quantity   BIGINT,
  CONSTRAINT cart_item_pk PRIMARY KEY (ID, CART_ID)
);