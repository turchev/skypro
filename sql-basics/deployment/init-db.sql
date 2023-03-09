-- ### Введение в SQL ###

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


-- # Выборка данных и вложенные запросы
--
-- ## Задание 1
--
-- 1. Доведите количество записей в таблице до 5
INSERT INTO employee (first_name, last_name, gender, age)
VALUES ('Николай', 'Карамзин', 'М', 59);
INSERT INTO employee (first_name, last_name, gender, age)
VALUES ('Михаил', 'Лермонтов', 'М', 26);
INSERT INTO employee (first_name, last_name, gender, age)
VALUES ('Агния', 'Барто', 'Ж', 80);
--
-- 2. Получите информацию об именах и фамилиях по всем сотрудникам.
-- Колонки должны называться «Имя» и «Фамилия».
SELECT first_name AS Имя, last_name AS Фамилия FROM employee;
--
-- 3. Получите всю информацию о сотрудниках, которые младше 30 или старше 50 лет.
SELECT first_name AS Имя, last_name AS Фамилия, gender Пол, age Возраст
FROM employee
WHERE age < 30 OR age > 50;
--
-- 4. Получите всю информацию о сотрудниках, которым от 30 до 50 лет.
SELECT first_name AS Имя, last_name AS Фамилия, gender Пол, age Возраст
FROM employee
WHERE age BETWEEN 30 AND 50;
--
-- 5. Выведите полный список сотрудников, которые отсортированы по фамилиям в обратном порядке.
SELECT first_name AS Имя, last_name AS Фамилия, gender Пол, age Возраст
FROM employee
ORDER BY last_name DESC;
--
-- 6. Выведите сотрудников, имена которых длиннее 4 символов.
SELECT first_name AS Имя, last_name AS Фамилия, gender Пол, age Возраст
FROM employee
WHERE character_length(first_name) > 4;

-- ## Задание 2
--
-- 1. Измените имена у двух сотрудников так, чтобы на 5 сотрудников было только 3 разных имени,
-- то есть чтобы получились две пары тезок и один сотрудник с уникальным именем.
UPDATE employee SET first_name='Михаил' WHERE id=2;
--
-- 2. Посчитайте суммарный возраст для каждого имени. Выведите в двух столбцах «имя» и «суммарный возраст».
SELECT first_name AS Имя, sum(age) AS Cуммарный_возраст
FROM employee
GROUP BY first_name;
--
-- 3. Выведите имя и самый юный возраст, соответствующий имени.
SELECT first_name AS Имя, min(age) AS Возраст
FROM employee
GROUP BY first_name;
--
-- 4. Выведите имя и максимальный возраст только для неуникальных имен. Результат отсортируйте по возрасту в порядке возрастания.
SELECT first_name AS Имя, max(age) AS Возраст
FROM employee
GROUP BY first_name
HAVING count(first_name) > 1
ORDER BY Возраст;

-- Вывод в консоль docker logs:
--
-- 2023-03-09 11:19:16.391 UTC [48] СООБЩЕНИЕ:  система БД готова принимать подключения
--  готово
-- сервер запущен
--
-- /usr/local/bin/docker-entrypoint.sh: running /docker-entrypoint-initdb.d/init-db.sql
-- CREATE DATABASE
--     Вы подключены к базе данных "skypro" как пользователь "postgres".
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
--
-- INSERT 0 1
-- INSERT 0 1
-- INSERT 0 1
--     Имя   |  Фамилия
-- ---------+-----------
--     Лев     | Толстой
--     Николай | Гумилев
--     Николай | Карамзин
--     Михаил  | Лермонтов
--     Агния   | Барто
-- (5 строк)
--
--     Имя   |  Фамилия  | Пол | Возраст
-- ---------+-----------+-----+---------
--     Лев     | Толстой   | М   |      82
--     Николай | Карамзин  | М   |      59
--     Михаил  | Лермонтов | М   |      26
--     Агния   | Барто     | Ж   |      80
-- (4 строки)
--
--     Имя   | Фамилия | Пол | Возраст
-- ---------+---------+-----+---------
--     Николай | Гумилев | М   |      35
-- (1 строка)
--
--     Имя   |  Фамилия  | Пол | Возраст
-- ---------+-----------+-----+---------
--     Лев     | Толстой   | М   |      82
--     Михаил  | Лермонтов | М   |      26
--     Николай | Карамзин  | М   |      59
--     Николай | Гумилев   | М   |      35
--     Агния   | Барто     | Ж   |      80
-- (5 строк)
--
--     Имя   |  Фамилия  | Пол | Возраст
-- ---------+-----------+-----+---------
--     Николай | Гумилев   | М   |      35
--     Николай | Карамзин  | М   |      59
--     Михаил  | Лермонтов | М   |      26
--     Агния   | Барто     | Ж   |      80
-- (4 строки)
--
-- UPDATE 1
--     Имя   | cуммарный_возраст
-- ---------+-------------------
--     Агния   |                80
--     Николай |                94
--     Михаил  |               108
--     (3 строки)
--
--     Имя   | Возраст
-- ---------+---------
--     Агния   |      80
--     Николай |      35
--     Михаил  |      26
--     (3 строки)
--
--     Имя   | Возраст
-- ---------+---------
--     Николай |      59
--     Михаил  |      82
--     (2 строки)
--
