USE alkemy;
CREATE TABLE members (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   facebook_url VARCHAR(255) NULL,
   instagram_url VARCHAR(255) NULL,
   linkedin_url VARCHAR(255) NULL,
   image VARCHAR(255) NOT NULL,
   `description` VARCHAR(255) NULL,
   is_deleted BIT(1) NULL,
   created_at datetime NULL,
   update_at datetime NULL,
   CONSTRAINT pk_members PRIMARY KEY (id)
);
