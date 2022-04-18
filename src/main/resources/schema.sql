USE alkemy;
CREATE TABLE IF NOT EXISTS news (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   content TEXT NOT NULL,
   image VARCHAR(255) NOT NULL,
   category_id BIGINT NOT NULL,
   is_deleted BIT(1) NOT NULL,
   created_at timestamp NOT NULL,
   updated_at timestamp NULL,
   CONSTRAINT pk_news PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted  BOOL         NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id    BIGINT,
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

CREATE TABLE IF NOT EXISTS members (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   facebook_url VARCHAR(255) NULL,
   instagram_url VARCHAR(255) NULL,
   linkedin_url VARCHAR(255) NULL,
   image VARCHAR(255) NOT NULL,
   description VARCHAR(255) NULL,
   is_deleted BIT(1) NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_members PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS categories(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN DEFAULT 0
);