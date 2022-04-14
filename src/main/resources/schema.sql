USE alkemy;

# Roles
CREATE TABLE roles
(
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    description VARCHAR(30) NULL,
    created_at  DATETIME    NOT NULL,
    updated_at  DATETIME    NOT NULL,
    is_deleted  BOOL        NOT NULL
);