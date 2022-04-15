USE alkemy;

CREATE TABLE roles
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(30)  NOT NULL,
    description VARCHAR(100) NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted  BOOL         NOT NULL DEFAULT FALSE
);

CREATE TABLE users
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id    INT,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    first_name VARCHAR(20)  NOT NULL,
    last_name  VARCHAR(20)  NOT NULL,
    email      VARCHAR(30)  NOT NULL UNIQUE,
    password   VARCHAR(50)  NOT NULL,
    photo      VARCHAR(100) NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted BOOL         NOT NULL DEFAULT FALSE
)