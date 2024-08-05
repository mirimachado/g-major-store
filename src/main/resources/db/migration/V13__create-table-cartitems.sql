CREATE TABLE cartitems (
id BIGSERIAL PRIMARY KEY NOT NULL,
quantity INTEGER NOT NULL,
cart_id BIGINT NOT NULL,
product_id BIGINT NOT NULL,
CONSTRAINT fk_cart_items_cart_id FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
CONSTRAINT fk_cart_items_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
CONSTRAINT uk_cart_items_cart_id_product_id UNIQUE (cart_id, product_id)
);