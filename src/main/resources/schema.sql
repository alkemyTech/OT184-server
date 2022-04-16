USE alkemy;

CREATE TABLE IF NOT EXISTS categories(

    id BIGINT PRIMARY KEY IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN

);