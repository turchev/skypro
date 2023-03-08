CREATE DATABASE skypro;

\connect skypro

CREATE TABLE employee
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    gender     VARCHAR(6)  NOT NULL,
    age        INT         NOT NULL
);


INSERT INTO employee (first_name, last_name, gender, age)
VALUES ('Александр', 'Пушкин', 'М', 37);
INSERT INTO employee (first_name, last_name, gender, age)
VALUES ('Лев', 'Толстой', 'М', 82);
INSERT INTO employee (first_name, last_name, gender, age)
VALUES ('Анна', 'Ахматова', 'Ж', 76);

SELECT * FROM employee;

UPDATE employee SET first_name='Николай', last_name='Гумилев', gender='М', age=35 WHERE id=1;

SELECT * FROM employee;

DELETE FROM employee WHERE id = 3;

SELECT * FROM employee;

-- Вывод в консоль docker logs:

-- 2023-03-08 18:40:55.735 UTC [48] СООБЩЕНИЕ:  система БД готова принимать подключения
--  готово
-- сервер запущен
--
-- /usr/local/bin/docker-entrypoint.sh: running /docker-entrypoint-initdb.d/init-db.sql
-- CREATE DATABASE
-- Вы подключены к базе данных "skypro" как пользователь "postgres".
-- CREATE TABLE
-- INSERT 0 1
-- INSERT 0 1
-- INSERT 0 1
--     id | first_name | last_name | gender | age
-- ----+------------+-----------+--------+-----
--     1 | Александр  | Пушкин    | М      |  37
--     2 | Лев        | Толстой   | М      |  82
--     3 | Анна       | Ахматова  | Ж      |  76
-- (3 строки)
--
-- UPDATE 1
--     id | first_name | last_name | gender | age
-- ----+------------+-----------+--------+-----
--     2 | Лев        | Толстой   | М      |  82
--     3 | Анна       | Ахматова  | Ж      |  76
--     1 | Николай    | Гумилев   | М      |  35
--     (3 строки)
--
-- DELETE 1
--     id | first_name | last_name | gender | age
-- ----+------------+-----------+--------+-----
--     2 | Лев        | Толстой   | М      |  82
--     1 | Николай    | Гумилев   | М      |  35
--     (2 строки)
