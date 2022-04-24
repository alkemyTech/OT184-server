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
    VALUES('news 1', 'content 1', '/img1.jpg', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 2', 'content 2', '/img2.jpg', 2, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 3', 'content 3', '/img3.jpg', 3, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 4', 'content 4', '/img4.jpg', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 5', 'content 5', '/img5.jpg', 2, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 6', 'content 6', '/img6.jpg', 3, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 7', 'content 7', '/img7.jpg', 3, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 8', 'content 8', '/img8.jpg', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 9', 'content 9', '/img9.jpg', 2, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');
INSERT INTO news (name, content, image, category_id, is_deleted, created_at, updated_at, `type`)
    VALUES('news 10', 'content 10', '/img10.jpg', 3, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'news');

INSERT INTO categories (name, description, image, created_at, updated_at, is_deleted)
    VALUES('category 1', 'description 1', '/img1.jpg', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO categories (name, description, image, created_at, updated_at, is_deleted)
    VALUES('category 2', 'description 2', '/img2.jpg', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO categories (name, description, image, created_at, updated_at, is_deleted)
    VALUES('category 3', 'description 3', '/img3.jpg', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);

INSERT INTO contacts (name, phone, email, message, is_deleted, deleted_at)
    VALUES('name 1', '1111111111', 'email1@contact.com', "message 1",0,null);
INSERT INTO contacts (name, phone, email, message, is_deleted, deleted_at)
    VALUES('name 2', '2222222222', 'email2@contact.com', "message 2",0,null);
INSERT INTO contacts (name, phone, email, message, is_deleted, deleted_at)
    VALUES('name 3', '3333333333', 'email3@contact.com', "message 3",0,null);
