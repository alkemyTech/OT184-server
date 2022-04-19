USE alkemy;

CREATE TABLE IF NOT EXISTS organizations (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   image VARCHAR(255) NULL,
   address VARCHAR(255) NULL,
   phone INT NULL,
   email VARCHAR(255) NULL,
   welcome_text VARCHAR(255) NULL,
   about_us_text VARCHAR(255) NULL,
   facebook VARCHAR(255) NULL,
   linkedin VARCHAR(255) NULL,
   instagram VARCHAR(255) NULL,
   created_at datetime NULL,
   updated_at datetime NULL,
   is_deleted BIT(1) NULL,
   CONSTRAINT pk_organizations PRIMARY KEY (id)
);
