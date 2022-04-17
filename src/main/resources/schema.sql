USE alkemy;
CREATE TABLE IF NOT EXISTS testimonials
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    image      VARCHAR(255)          NULL,
    content    VARCHAR(255)          NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    is_deleted BIT(1)                NULL,
    CONSTRAINT pk_testimonials PRIMARY KEY (id)
);