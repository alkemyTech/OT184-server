USE alkemy;

CREATE TABLE IF NOT EXISTS roles
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted  BOOL         NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id    INT,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    photo      VARCHAR(255) NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted BOOL         NOT NULL DEFAULT FALSE
)