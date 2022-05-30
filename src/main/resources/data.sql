INSERT INTO brands (brand_id, description) VALUES
  (1, 'ZARA 1'),
  (2, 'ZARA 2'),
  (3, 'ZARA 3');

INSERT INTO products (product_id, code, description) VALUES
  (1, '35455', 'PRODUCT 1'),
  (2, '35456', 'PRODUCT 2'),
  (3, '35457', 'PRODUCT 3'),
  (4, '35458', 'PRODUCT 4'),
  (5, '35459', 'PRODUCT 5');

INSERT INTO currencies (currency_id, iso_code, description) VALUES 
  (1, 'EUR', 'EUROS'),
  (2, 'USD', 'DOLARES AMERICANOS');

INSERT INTO price_lists (price_list_id, brand_id, start_date, end_date, product_id, price, currency_id, priority) VALUES
  (1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35.50, 1, 0),
  (2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 1, 1),
  (3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 1, 1),
  (4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 1, 1);

-- Additional test data
INSERT INTO price_lists (price_list_id, brand_id, start_date, end_date, product_id, price, currency_id, priority) VALUES
  (5, 2, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35.50, 1, 0),
  (6, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 1, 1),
  (7, 3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 1, 1),
  (8, 2, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 2, 35.50, 1, 0),
  (9, 2, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 25.45, 1, 1),
  (10, 3, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 2, 30.50, 1, 1);