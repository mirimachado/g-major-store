CREATE TABLE products (
id BIGSERIAL PRIMARY KEY NOT NULL,
name VARCHAR NOT NULL,
description VARCHAR NOT NULL,
price DOUBLE PRECISION NOT NULL,
quantity INTEGER NOT NULL,
active BOOLEAN NOT NULL DEFAULT TRUE,
category_id BIGINT NOT NULL,
CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
CONSTRAINT uk_product_name_description_category_id UNIQUE (name, description, category_id)
);