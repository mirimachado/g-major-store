CREATE TABLE addresses (
id BIGSERIAL PRIMARY KEY NOT NULL,
state VARCHAR NOT NULL,
city VARCHAR NOT NULL,
postal_code INTEGER NOT NULL,
neighborhood VARCHAR NOT NULL,
street VARCHAR NOT NULL,
number VARCHAR NOT NULL,
additional_info VARCHAR NOT NULL,
active BOOLEAN NOT NULL DEFAULT TRUE,
person_id BIGINT NOT NULL,
CONSTRAINT fk_address_person_id FOREIGN KEY (person_id) REFERENCES persons(id) ON DELETE CASCADE,
CONSTRAINT uk_address_person_id_post_code UNIQUE (person_id, postal_code)
);