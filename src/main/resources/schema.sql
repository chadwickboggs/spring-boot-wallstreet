
CREATE TABLE stock (
    id SERIAL PRIMARY KEY,
    name VARCHAR(6),
    price FLOAT,
    create_date TIMESTAMP,
    update_date TIMESTAMP
);
