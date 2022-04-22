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

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('miguel', 'berrio', 'miguelberrio@gmail.com', 'mberrio', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('andres', 'puebla', 'andrespuebla@gmail.com', 'apuebla', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('cristian', 'fernandez', 'cristianfernandez@gmail.com', 'cfernandez', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('francisco', 'romero', 'franciscoromero@gmail.com', 'fromero', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('lucas', 'saleme', 'lucassaleme@gmail.com', 'lsaleme', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('nicolas', 'pucci', 'nicolaspucci@gmail.com', 'npucci', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('lucas', 'paoliello', 'lucaspaoliello@gmail.com', 'lpaoliello', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('bill', 'gates', 'billgates@gmail.com', 'bgates', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('steve', 'jobs', 'stevejobs@gmail.com', 'sjobs', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('torvalds', 'linus', 'torvaldslinus@gmail.com', 'tlinus', 1);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('alejandro', 'abondano', 'alejandroabondano@gmail.com', 'aabondano', 2);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('brigite', 'polanco', 'brigitepolanco@gmail.com', 'apuebla', 3);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('camilo', 'gomez', 'camilogomez@gmail.com', 'cgomez', 4);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('daniela', 'guzman', 'danielaguzman@gmail.com', 'dguzman', 2);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('estewil', 'quesada', 'estewilquesada@gmail.com', 'equesada', 3);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('fabian', 'fino', 'fabianfino@gmail.com', 'ffino', 4);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('gabriel', 'nieto', 'gabrielnieto@gmail.com', 'gnieto', 2);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('hugo', 'camargo', 'hugocamargo@gmail.com', 'hcamargo', 3);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('ivan', 'coral', 'ivancoral@gmail.com', 'icoral', 4);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('jenny', 'sanchez', 'jennysanchez@gmail.com', 'jsanchez', 3);

INSERT INTO users (first_name, last_name, email, password, role_id)
    VALUE ('daniel', 'cruz', 'danielcruz@gmail.com', 'dcruz', 2);