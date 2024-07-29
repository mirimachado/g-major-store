AlTER TABLE categories ADD COLUMN active BOOLEAN;
UPDATE users SET active = true;
