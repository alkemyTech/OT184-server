USE alkemy;

# Roles
CREATE TABLE roles
(
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    description VARCHAR(100) NULL,
    created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted  BOOL        NOT NULL DEFAULT FALSE
);