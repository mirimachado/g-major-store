ALTER TABLE users
ADD CONSTRAINT uk_users_username UNIQUE (username),
ADD CONSTRAINT uk_users_email UNIQUE (email);