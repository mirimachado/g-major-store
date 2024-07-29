CREATE TABLE persons (
id BIGSERIAL PRIMARY KEY NOT NULL,
name VARCHAR NOT NULL,
phone VARCHAR NOT NULL,
cpf VARCHAR(11) NOT NULL,
user_id BIGINT NOT NULL,
CONSTRAINT fk_person_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
CONSTRAINT uk_person_cpf UNIQUE (cpf)
);