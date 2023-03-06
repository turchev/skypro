

# Курсовая работа 3-го курса. Техническая часть

> Привет!

На связи курсовая работа 3-го курса, в рамках которой вы сделаете веб-приложение для сотрудников склада интернет-магазина носков.
>

В работе вы будете использовать навыки и инструменты из уроков, рассмотренных на 2-м и 3-м курсах, поэтому мы рекомендуем вам повторить уроки, прежде чем выполнять задание.

В тексте задания вы найдете подсказки к выполнению: рекомендуем не пользоваться ими сразу, а только при необходимости, если вы понимаете, что не можете найти правильный ход решения.

<aside>
💡 Данное задание построено на базе реального тестового задания на позицию Junior Java-разработчика.

</aside>


## **Описание задачи**

Нужно создать веб-приложение, с помощью которого склад может учитывать и автоматизировать учет товаров на складе интернет-магазина носков.

Пользователь (работник склада) должен иметь возможность:

- учитывать приход и отпуск носков;
- узнать общее количество носков определенного цвета и состава в данный момент времени;
- дополнительно иметь возможность парсить (читать и преобразовывать данные) файлы с данными по товару.

Внешний интерфейс приложения представлен в виде REST API.

Товар имеет следующие характеристики:

- цвет носков,
- *Подсказка*

  *Все цвета должны быть заранее перечислены и зафиксированы без возможности добавления. То есть для этого необходимо использовать перечисления.*

- размер носков
- *Подсказка*

  *Все размеры должны быть заранее перечислены и зафиксированы без возможности добавления. То есть для этого необходимо использовать перечисления.*

- состав носков,
- *Подсказка*

  *Должно выражаться в % соотношение хлопка в составе от 0 до 100 только целыми числами.*

- наличие на складе.
- *Подсказка*

  *Нельзя отгрузить носков больше, чем осталось на складе.*

## **Список URL HTTP-методов**

### **POST /api/socks**

Регистрирует приход товара на склад.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

- colorType — цвет носков, строка (например, black, red, yellow);
- size — размер носков, числовое значение (например, размер 36 или размер 37,5);
- cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
- quantity — количество пар носков, целое число больше 0.

Результаты:

- HTTP 200 — удалось добавить приход;
- HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
- HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны.

### **PUT /api/socks**

Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные, но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

- colorType — цвет носков, строка;
- size — размер носков, числовое значение;
- cottonPart — процентное содержание хлопка в составе носков;
- quantity — количество пар носков, целое число больше 0.

В данном методе необходимо добавить проверку на доступность остатка товара на складе.

Результаты:

- HTTP 200 — удалось произвести отпуск носков со склада;
- HTTP 400 — товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат;
- HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны.

### **GET /api/socks**

Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса. В данном методе количество носков остается неизменным, так как мы запрашиваем информацию о товарах на складе.

Параметры запроса передаются в URL:

- colorType — цвет носков, строка;
- size — размер носков, числовое значение;
- cottonMin — минимальное значение хлопка в товаре;
- cottonMax — максимальное значение хлопка в товаре.

Результаты:

- HTTP 200 — запрос выполнен, результат в теле ответа в виде строкового представления целого числа;
- HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
- HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны.

### DELETE **/api/socks**

Регистрирует списание испорченных (бракованных) носков.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

- colorType — цвет носков, строка;
- size — размер носков, числовое значение;
- cottonPart — процентное содержание хлопка в составе носков;
- quantity — количество пар носков, целое число больше 0.

Результаты:

- HTTP 200 —  запрос выполнен, товар списан со склада;
- HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
- HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны.

### Примеры запросов:

- POST /api/socks?colorType=red&36&cottonPart=40&quantity=5 — добавляет на склад пять пар носков красного цвета 36-го размера с долей хлопка в составе 40%;
- PUT /api/socks?colorType=red&36&cottonPart=40&quantity=5 — регистрирует отпуск со склада пяти пар носков красного цвета 36-го размера с долей хлопка в составе 40%;
- GET /api/socks?colorType=red&36&cottonmin=90 — должен вернуть общее количество красных носков 36-го размера с долей хлопка более 90%;
- GET /api/socks?colorType=red&36&cottonmax=10 — должен вернуть общее количество красных носков 36-го размера с долей хлопка менее 10%;
- DELETE /api/socks?colorType=red&36&cottonPart=40&quantity=5 — регистрирует списание брака со склада в количестве пяти пар носков красного цвета 36-го размера с долей хлопка в составе 40%.

## Базовый уровень выполнения (обязательный для всех)

- Веб-приложение выполнено в виде RESTful-сервиса.
- Веб-приложение основано на Spring(Boot) Framework.
- Написаны все CRUD-методы :
    - POST — на склад можно добавить новый товар;
    - PUT — можно забрать товар со склада;
    - GET — можно получить данные о товаре на складке: общее количество и данные по составу;
    - DELETE — со склада можно списать бракованный товар.
- Все CRUD-методы работают и возвращают значения согласно условию задания.
- Отработаны ошибки при некорректно введенных данных.
- Переменные, объекты, классы и методы имеют корректные названия согласно правилам написания кода в языке Java.
- В качестве UI-части приложения используется API-клиент (Swagger-ui).
- Задание оформлено и сдано на платформу как GitHub-репозиторий.

## Средний уровень

<aside>
💡 Приступайте к данному уровню, если вы справились с базовым уровнем и у вас есть время до окончания сдачи курсовой. Либо вы уже получили отличную оценку от наставника и хотите дополнить свое приложение.

</aside>

Создайте три эндпоинта, которые будут выполнять две задачи:

Первая задача - экспорт данных в текущем состоянии. Другими словами, эндопоинт должен формировать из данных в памяти JSON, записывать его в файл и выгружать по запросу.

Вторая задача – импорт данных. То есть второй эндпоинт принимает на вход json-файл с данными и заменяет ими данные в памяти.

Также реализуйте возможность сохранять операции приемки и выдачи носков в памяти и выгружать их в виде JSON-файла и обратно – загружать данные в приложение из JSON-файла. Для каждой операции нужно сохранять:

- Тип (приемка, списание или выдача);
- Дата и время операции;
- Количество носков;
- Размер;
- Содержание хлопка;
- Цвет.