
Задание

В рамках курса мы будем создавать приложение для сайта рецептов.

Ваша задача сегодня — подключить и настроить Spring к проекту, написать ваши первые запросы.

Создайте новый проект веб-приложения в IDEA, подключите к нему в зависимостях Spring, настройте запуск приложения.

Добавьте описание проекта, которое включает:

* название проекта,
* функции проекта,
* с помощью каких технологий будет реализован проект,
* на каком языке проект написан.

Создайте контроллер с двумя эндпоинтами:
* Первый должен обрабатывать запросы, и странице должен отображаться текст «Приложение запущено»
* Второй запрос должен обрабатывать запросы с конкретным URL-адресом http://localhost:8080/info и возвращать:
    * имя ученика,
    * название вашего проекта,
    * дату создания проекта,
    * описание проекта в свободной форме.

В качестве выполненного задания пришлите ссылку на pull-request вашего проекта на GitHub.
Критерии проверки

    Создан новый проект.
    Создано описание проекта в свободной форме.
    К проекту подключен Spring Framework.
    В написанном приложении GET-запрос обрабатывает запросы без указания страницы.
    Второй GET-запрос в приложении обрабатывает запрос с URL-адресом http://localhost:8080/info и возвращает:
        - имя ученика,
        - название проекта,
        - дату создания проекта,
        - описание проекта.
    Данные приложения выводятся в браузер.
 
**Задание 4**

Продолжим разрабатывать приложение для рецептов. Вам необходимо создать сервис, который будет хранить рецепты и возвращать рецепты по его идентификатору.

Храниться рецепты должны в карте в формате <номер рецепта, рецепт>.

Поля класса рецепта должны содержать:

* Название в формате строки;
* Время приготовления в минутах в формате целого положительного числа;
* Ингредиенты в формате списка объектов;
* Шаги приготовления в формате списка строк.

Поля класса ингредиента должны содержать:

* Название в формате строки;
* Количество ингредиентов в формате целого положительного числа;
* Единица измерения в формате строки.

В сервисе должны быть реализованы методы:

1. Добавления нового рецепта.
В метод передается заполненный объект класса рецепта, который сохраняется в карте и получает свой порядковый номер.

2. Получение рецепта.
В метод передается порядковый номер рецепта, на выходе мы получаем из карты нужный объект.

Создайте такой же сервис, но для работы с ингредиентами:

* Храниться ингредиенты должны в карте в формате <идентификатор, ингредиент>;
* В сервисе должны быть методы для добавления нового ингредиента и получения его по идентификатору. Можно делать по аналогии с сервисом рецептов.

Создайте контроллеры и API для создания и получения рецептов и ингредиентов.

    💡 Не забудьте проверить исключения в случае некорректно введенных данных или значения null. 
    Какие данные будут считаться некорректными, определите самостоятельно исходя из условия задачи и описания формата параметров классов.

Критерии оценки:

* Создан класс Recipe (рецепт);
* Создан класс Ingredient (ингредиент);
* Создан интерфейсы и классы сервисов с методами;
* Созданы контроллеры для создания и получения рецептов и ингредиентов.


   **Задание**

Продолжаем работать с приложением по рецептам. К уже созданной структуре добавьте операции редактирования и удаления рецептов и ингредиентов.

_Подсказка_

_Нужно создать четыре метода:_

* метод редактирования рецептов,
* метод удаления рецептов,
* метод редактирования ингредиентов,
* метод удаления ингредиентов.

Исправьте код в соответствии с нормами REST:

* Структура URL. Path должен начинаться с названия сущности, идентификатор должен быть частью path. Остальные параметры — в строке URL. Создание и редактирование — через тело запроса.
* Названия методов должны отражать функциональность.
* Названия параметров запросов должны быть короткими, понятными, отражать предназначение.
* Разнесение эндпоинтов между контроллерами: работа с рецептами отдельно от работы с ингредиентами.

В результате у вас должно получиться приложение, в котором должно быть следующее (для каждого пункта должен быть соответствующий эндпоинт):

1. Добавление ингредиента.
2. Редактирование ингредиента по id.
3. Удаление ингредиента.
4. Получение информации об ингредиенте по id.
5. Получение полного списка ингредиентов.
6. Добавление рецепта.
7. Редактирование рецепта по id.
8. Удаление рецепта по id.
9. Получение рецепта по id.
10. Получение списка всех рецептов.

Дополнительная часть задания для продвинутого уровня (необязательно к выполнению):

    Поиск рецептов по id ингредиента.
    Поиск рецепта по нескольким ингредиентам .
    Вывод рецептов постранично по 10 штук.

    💡 Если у вас остались незакрытые комментарии и доработки по предыдущим этапам работы, обязательно выполните их.

Критерии оценки

    Реализована вся требуемая функциональность.
    Эндпоинты сгруппированы в разных контроллерах.
    URL сформированы согласно правилам REST, описанным в задаче.

**Задание**

Подключите к вашему проекту из прошлых заданий (книга рецептов) три библиотеки в проект:

* Lombok (удалить всё, кроме полей из POJO-классов, и поставить нужные аннотации).
* Apache Commons (используйте утилитные методы из Apache Commons там, где это необходимо).
* Swagger (настройте генерацию UI с помощью Swagger, добавьте описание к контроллерам и эндпоинтам).

_Подсказка:_

Ознакомиться со списком и описанием методов данного класса можно в документации по ссылке: https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html

_Критерии проверки_

* К проекту подключена библиотека commons-lang3.
* К проекту подключена библиотека Lombok.
* К проекту подключена библиотека Swagger.
* Библиотеки подключены с помощью Maven.
* В проекте использованы утилитарные методы из Apache Commons.
* Есть описание ко всем контроллерам и эндпоинтам.

**Задание**

Продолжим работать с проектом по книге рецептов. Вам необходимо настроить сохранение загруженных рецептов и добавленных ингредиентов в файлы.

Важно: сохранение загруженных рецептов и добавленных ингредиентов должно происходить в разные файлы.

_Требования к файлам:_

* Формат json;
* Путь к файлу должен быть в application.properties (_Описание этого шага можно найти в шпаргалке к уроку и в видео_).
* Сохранение должно происходить при любом изменении рецептов и/или ингредиентов.

При запуске приложения данные нужно читать из файла с помощью метода с аннотацией @PostConstruct в сервисе.

_Описание этого шага можно найти в видеоуроке и шпаргалке к уроку._

Обработайте ошибки, которые могут возникнуть, — самостоятельно определите, какие это могут быть ошибки.

    💡 Отладочная информация должна выводиться в консоль e.printStackTrace(). 
    Описание этого шага можно найти в видеоуроке и шпаргалке к уроку

В результате у вас должен получиться сервис, в котором есть два метода: сохранение данных в файл и чтение файла с диска. В сервисе с рецептами должен быть реализован метод с аннотацией @PostConstruct, который обращается к сервису с файлами, получает данные и записывает их в поле класса.
_Критерии оценки задания_

* В сервисе реализован метод сохранения данных в формате json в файле на жестком диске.
* В сервисе реализован метод чтения данных с диска.
* В сервисе с рецептами должен быть реализован метод с аннотацией @PostConstruct.
* В проекте обработаны исключения: отладочная информация выводится в консоль _e.printStackTrace()_.
* Данные по рецептам и по ингредиентам сохраняются в разные файлы.

**Задание**

Создайте три эндпоинта.

Функционал и требования к ним:

1. Первый эндпоинт позволяет скачать все рецепты в виде json-файла.
2. Второй эндпоинт принимает json-файл с рецептами и заменяет сохраненный на жестком (локальном) диске файл на новый.
3. Третий эндпоинт принимает json-файл с ингредиентами и заменяет сохраненный на жестком (локальном) диске файл на новый.

В результате у вас должно получиться дописанное приложение, в котором реализовано три эндопинта, перечисленные выше.
Критерии оценки:

* Реализован отдельный контроллер, который работает с файлами
* Созданы три метода в контроллере, которые реализуют работу с файлами в формате json
* Обработаны ошибки в дописанном приложении

**Задание**

В приложении по работе с кулинарными рецептами создайте один эндпоинт, который позволяет скачать все рецепты из приложения в одном файле в виде:

_Пример скачанного рецепта_

> Рецепт 1
### Сырники из творога

**Время приготовления: 30 минут.**

**Ингредиенты:**

* Творог — 350 г.
* Куриное яйцо — 2 шт.
* Пшеничная мука — 6 ст.л.
* Сахар — 2 ст.л.

**Инструкция приготовления:**

1. Смешайте весь творог с яйцами, сахаром и тщательно всё перемешайте.
2. Всыпьте в творог муку и тщательно перемешайте.
3. Поставьте сковороду на средний огонь и налейте в нее подсолнечное масло.
4. Слепите несколько небольших шариков из получившейся творожной массы и положите их на тарелку. Затем по очереди обкатывайте творожные шарики в муке и выкладывайте на сковороду.
5. Обжаривайте сырники 1–2 минуты до появления золотистой корочки. Затем переверните их на другую сторону и также обжарьте до золотистого состояния.
6. Повторяйте, пока творог не закончится.

Обработайте исключительные ситуации. При возникновении исключений допишите приложение так, что клиенту будет отправляться HTTP-ответ с соответствующим кодом.

_Подсказка_

Наиболее часто используемые статусы ответа:

* 200 — всё хорошо, запрос выполнился.
* 400 — есть ошибка в параметрах запроса.
* 404 — URL неверный или такого действия нет в веб-приложении.
* 500 — во время выполнения запроса произошла ошибка на сервере.

При обработке ошибок обратите внимание в первую очередь на:

1. Отсутствие данных по id;
2. Ошибки при работе с потоками данных и файлами;
3. Отсутствие рецептов или ингредиентов.

В результате у вас должно выйти полностью функционирующее приложение, в которое можно загружать рецепты, удалять их, менять, получать данные через HTTP-запросы. В вашем приложении обработаны ошибки и исключения, при неверном вводе или выводе данных клиенту отправляется HTTP-ответ с соответствующим кодом. 


