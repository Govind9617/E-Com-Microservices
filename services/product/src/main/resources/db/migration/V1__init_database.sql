CREATE TABLE IF NOT EXISTS category (
  id INTEGER NOT NULL PRIMARY KEY,
  description VARCHAR(255),
  name VARCHAR(255)
);CREATE TABLE IF NOT EXISTS product (
  id INTEGER NOT NULL PRIMARY KEY,
  description VARCHAR(255),
  name VARCHAR(255),
  price NUMERIC(38, 2),
  available_Quantity DOUBLE PRECISION NOT NULL,
  category_id INTEGER CONSTRAINT fk1mtsbur82frn64de7balymq9s REFERENCES category
);CREATE sequence IF NOT EXISTS category_seq increment BY 50;CREATE sequence IF NOT EXISTS product_seq increment BY 50;
