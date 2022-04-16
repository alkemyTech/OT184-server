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
   type VARCHAR(255) NOT NULL,
   CONSTRAINT pk_news PRIMARY KEY (id)
);