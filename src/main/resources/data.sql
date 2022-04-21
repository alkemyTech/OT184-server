USE alkemy;

INSERT INTO roles (name, description)
VALUES ('admin', 'Full application access');

INSERT INTO roles (name, description)
VALUES ('user', 'User level access');

INSERT INTO roles (name, description)
VALUES ('maintainer', 'Can read and update resources');

INSERT INTO roles (name, description)
VALUES ('developer', 'Developer access level');

INSERT INTO users (role_id, first_name, last_name, email, password, photo)
VALUES (1, 'Juan', 'Gomez', 'juangomez@mail.com', '12345678', 'https://www.somebucket.com/photos/image.jpg');

INSERT INTO users (role_id, first_name, last_name, email, password, photo)
VALUES (2, 'Maria', 'Perez', 'mariaperez@mail.com', 'qwerty', 'https://www.somebucket.com/photos/image.jpg');

INSERT INTO users (role_id, first_name, last_name, email, password)
VALUES (3, 'Jose', 'Gonzalez', 'josegonzalez@mail.com', 'zxcvb');

INSERT INTO users (role_id, first_name, last_name, email, password)
VALUES (1, 'Debora', 'Zalazar', 'deborazalar@mail.com', 'asdfgh');

INSERT INTO activities (name, content, image)
    VALUE ('activity one', 'do activity one content', 'https://www.somebucket.com/activity_one.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity two', 'do activity two content', 'https://www.somebucket.com/activity_two.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity three', 'do activity three content', 'https://www.somebucket.com/activity_three.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity four', 'do activity four content', 'https://www.somebucket.com/activity_four.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity five', 'do activity five content', 'https://www.somebucket.com/activity_five.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity six', 'do activity six content', 'https://www.somebucket.com/activity_six.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity seven', 'do activity seven content', 'https://www.somebucket.com/activity_seven.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity eight', 'do activity eight content', 'https://www.somebucket.com/activity_eight.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity nine', 'do activity nine content', 'https://www.somebucket.com/activity_nine.jpg');

INSERT INTO activities (name, content, image)
    VALUE ('activity ten', 'do activity ten content', 'https://www.somebucket.com/activity_ten.jpg');

INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('noticia1', 'content1', '/img1.jpg', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('noticia2', 'content2', '/img2.jpg', 2, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('noticia3', 'content3', '/img3.jpg', 3, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');

INSERT INTO categories (name, description, image, created_at, updated_at, is_deleted)
    VALUES('categoria1', 'descripcion1', '/img1.jpg', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO categories (name, description, image, created_at, updated_at, is_deleted)
    VALUES('categoria2', 'descripcion2', '/img2.jpg', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO categories (name, description, image, created_at, updated_at, is_deleted)
    VALUES('categoria3', 'descripcion3', '/img3.jpg', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
