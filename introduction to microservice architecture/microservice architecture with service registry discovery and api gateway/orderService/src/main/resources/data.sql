-- =========================
-- Orders
-- =========================

INSERT INTO orders (order_status, total_price)
VALUES
('PENDING', 174997.00),
('CONFIRMED', 174998.00),
('SHIPPED', 109998.00),
('DELIVERED', 114999.00),
('CANCELLED', 59999.00);

-- =========================
-- Order Items
-- =========================

INSERT INTO order_item (product_id, quantity, order_id)
VALUES
-- Order 1
(101, 2, 1),
(103, 1, 1),

-- Order 2
(102, 1, 2),
(104, 1, 2),

-- Order 3
(105, 2, 3),
(108, 1, 3),

-- Order 4
(110, 1, 4),
(115, 2, 4),

-- Order 5
(120, 1, 5),
(125, 3, 5);