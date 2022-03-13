CREATE TABLE IF NOT EXISTS author(
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    country VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS book(
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    price DECIMAL(5, 2),
    amount INT,
    points DECIMAL(2, 1),
    author_id INT NOT NULL REFERENCES author(id)
);