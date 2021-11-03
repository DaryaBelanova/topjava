DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2021-05-30 10:00:00', 'Завтрак', 500),
       (100000, '2021-05-30 13:00:00', 'Обед', 1000),
       (100000, '2021-05-30 17:00:16', 'Ужин', 800),
       (100001, '2021-10-25 11:34:37', 'Завтрак', 550),
       (100001, '2021-10-25 14:46:15', 'Обед', 1200),
       (100001, '2021-10-25 19:00:07', 'Ужин', 1400),
       (100001, '2021-10-25 23:57:01', 'Ночной перекус', 920)