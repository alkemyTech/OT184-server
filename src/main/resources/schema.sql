USE alkemy;
CREATE TABLE news (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   content TEXT NOT NULL,
   image VARCHAR(255) NOT NULL,
   category_id BIGINT NOT NULL,
   is_deleted BIT(1) NULL,
   created_at timestamp NULL,
   updated_at timestamp NULL,
   CONSTRAINT pk_news PRIMARY KEY (id)
);