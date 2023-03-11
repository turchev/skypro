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


-- # Работа с несколькими таблицами
--
-- ### Задание 1
--
-- 1. Создайте таблицу city с колонками city_id и city_name.
CREATE TABLE city
(
    city_id   BIGSERIAL   NOT NULL PRIMARY KEY,
    city_name VARCHAR(60) NOT NULL
);
--
-- 2. Добавьте в таблицу employee колонку city_id.
ALTER TABLE employee
    ADD city_id BIGINT;
--
-- 3. Назначьте ее внешним ключом и свяжите с таблицей city.
ALTER TABLE employee
    ADD FOREIGN KEY (city_id) REFERENCES city (city_id);
--
-- 4. Заполните таблицу city и назначьте работникам соответствующие города.
INSERT INTO city (city_name) VALUES ('Кронштадт');
UPDATE employee
SET city_id = (SELECT c.city_id
               FROM city c
               WHERE c.city_name = 'Кронштадт')
WHERE last_name = 'Гумилев';
--
INSERT INTO city (city_name) VALUES ('Москва');
UPDATE employee
SET city_id = (SELECT c.city_id
               FROM city c
               WHERE c.city_name = 'Москва')
WHERE last_name = 'Лермонтов';
--
INSERT INTO city (city_name) VALUES ('Симбирск');
UPDATE employee
SET city_id = (SELECT c.city_id
               FROM city c
               WHERE c.city_name = 'Симбирск')
WHERE last_name = 'Карамзин';
--
INSERT INTO city (city_name) VALUES ('Тула');
UPDATE employee
SET city_id = (SELECT c.city_id
               FROM city c
               WHERE c.city_name = 'Тула')
WHERE last_name = 'Толстой';
--
INSERT INTO city (city_name) VALUES ('Самара');
INSERT INTO city (city_name) VALUES ('Калининград');
INSERT INTO city (city_name) VALUES ('Брянск');
--
-- ### Задание 2
--
-- 1. Получите имена и фамилии сотрудников, а также города, в которых они проживают.
SELECT e.first_name Имя,
       e.last_name  Фамилия,
       c.city_name  Город
FROM employee e
         LEFT JOIN city c ON e.city_id = c.city_id;
--
-- 2. Получите города, а также имена и фамилии сотрудников, которые в них проживают.
-- Если в городе никто из сотрудников не живет, то вместо имени должен стоять null.
SELECT c.city_name  Город,
       e.first_name Имя,
       e.last_name  Фамилия
FROM employee e
         RIGHT JOIN city c ON e.city_id = c.city_id;
--
-- 3. Получите имена всех сотрудников и названия всех городов. Если в городе не живет никто из сотрудников,
-- то вместо имени должен стоять null. Аналогично, если города для какого-то из сотрудников нет в списке, так же должен быть получен null.
SELECT c.city_name  Город,
       e.first_name Имя,
       e.last_name  Фамилия
FROM employee e
         FULL JOIN city c ON e.city_id = c.city_id;
--
-- 4. Получите таблицу, в которой каждому имени должен соответствовать каждый город.
SELECT e.first_name Имя,
       e.last_name  Фамилия,
       c.city_name  Город
FROM employee e
         CROSS JOIN city c
ORDER BY Фамилия;

-- Вывод в консоль docker logs (не весь, убрал по предыдущим заданиям):
--
--2023-03-11 20:00:18.227 UTC [48] СООБЩЕНИЕ:  система БД готова принимать подключения
-- готово
-- сервер запущен
--
-- /usr/local/bin/docker-entrypoint.sh: running /docker-entrypoint-initdb.d/init-db.sql

-- CREATE TABLE
-- ALTER TABLE
-- ALTER TABLE
-- INSERT 0 1
-- UPDATE 1
-- INSERT 0 1
-- UPDATE 1
-- INSERT 0 1
-- UPDATE 1
-- INSERT 0 1
-- UPDATE 1
-- INSERT 0 1
-- INSERT 0 1
-- INSERT 0 1
--     Имя   |  Фамилия  |   Город
-- ---------+-----------+-----------
--     Агния   | Барто     |
--     Николай | Гумилев   | Кронштадт
--     Михаил  | Лермонтов | Москва
--     Николай | Карамзин  | Симбирск
--     Михаил  | Толстой   | Тула
-- (5 строк)
--
--     Город    |   Имя   |  Фамилия
-- -------------+---------+-----------
--     Кронштадт   | Николай | Гумилев
--     Москва      | Михаил  | Лермонтов
--     Симбирск    | Николай | Карамзин
--     Тула        | Михаил  | Толстой
--     Самара      |         |
--     Калининград |         |
--     Брянск      |         |
-- (7 строк)
--
--     Город    |   Имя   |  Фамилия
-- -------------+---------+-----------
--     | Агния   | Барто
--     Кронштадт   | Николай | Гумилев
--     Москва      | Михаил  | Лермонтов
--     Симбирск    | Николай | Карамзин
--     Тула        | Михаил  | Толстой
--     Самара      |         |
--     Калининград |         |
--     Брянск      |         |
-- (8 строк)
--
--     Имя   |  Фамилия  |    Город
-- ---------+-----------+-------------
--     Агния   | Барто     | Самара
--     Агния   | Барто     | Калининград
--     Агния   | Барто     | Москва
--     Агния   | Барто     | Симбирск
--     Агния   | Барто     | Брянск
--     Агния   | Барто     | Тула
--     Агния   | Барто     | Кронштадт
--     Николай | Гумилев   | Кронштадт
--     Николай | Гумилев   | Брянск
--     Николай | Гумилев   | Самара
--     Николай | Гумилев   | Симбирск
--     Николай | Гумилев   | Калининград
--     Николай | Гумилев   | Тула
--     Николай | Гумилев   | Москва
--     Николай | Карамзин  | Тула
--     Николай | Карамзин  | Москва
--     Николай | Карамзин  | Брянск
--     Николай | Карамзин  | Самара
--     Николай | Карамзин  | Калининград
--     Николай | Карамзин  | Кронштадт
--     Николай | Карамзин  | Симбирск
--     Михаил  | Лермонтов | Тула
--     Михаил  | Лермонтов | Кронштадт
--     Михаил  | Лермонтов | Москва
--     Михаил  | Лермонтов | Симбирск
--     Михаил  | Лермонтов | Самара
--     Михаил  | Лермонтов | Калининград
--     Михаил  | Лермонтов | Брянск
--     Михаил  | Толстой   | Тула
--     Михаил  | Толстой   | Калининград
--     Михаил  | Толстой   | Симбирск
--     Михаил  | Толстой   | Москва
--     Михаил  | Толстой   | Брянск
--     Михаил  | Толстой   | Самара
--     Михаил  | Толстой   | Кронштадт
-- (35 строк)
