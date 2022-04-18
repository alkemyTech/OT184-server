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

