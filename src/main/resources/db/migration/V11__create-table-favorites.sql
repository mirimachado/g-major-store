CREATE TABLE favorites (
id BIGSERIAL PRIMARY KEY NOT NULL,
user_id BIGINT NOT NULL,
product_id BIGINT NOT NULL,
CONSTRAINT fk_favorite_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
CONSTRAINT fk_favorite_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
CONSTRAINT uk_favorite_user_id_product_id UNIQUE (user_id, product_id)
);