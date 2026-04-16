CREATE TABLE election (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    status VARCHAR(50),
    start_date TIMESTAMP,
    end_date TIMESTAMP
);