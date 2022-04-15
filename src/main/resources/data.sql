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