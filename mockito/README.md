# Домашнее задание
   
##   Задание

Создайте небольшое приложение, в котором будут созданы Класс `User` с обязательным полем `name` (другие поля по желанию), а также слои `Dao` и `Service`. 
В классе `UserDaoImpl` создайте лист юзеров и в конструкторе наполните его несколькими объектами класса `User`. 
Также в этом классе нужно реализовать 2 метода `getUserByName`, который будет принимать имя и возвращать юзера по этому имени или `null` в противном случае 
и метод `findAllUsers`, который будет возвращать список юзеров. В классе `UserService` нужно реализовать один метод `checkUserExist`, 
который принимает юзера и, обращаясь к дао, будет искать юзера в списке и возвращать `true` или `false`, в зависимости от результата.

Теперь нам нужно создать два класса для тестирования.

1. Первый класс `UserDaoTest` будет без применения `Mockito`. В этом классе тестируем метод `getUserByName` из класса `UserDaoImpl`. 
Сначала нужно задать нового юзера с существующим именем из нашего списка и убедиться, что метод вернет true. 
Вторым тестовым методом будет обратная проверка на то, что метод вернет `null`, если мы зададим пользователя с именем, не существующем в списке.
2. Вторым классом для тестирования будет класс `UserServiceTest`, в котором нам необходимо протестировать метод `checkUserExist` из класса `UserService`. 
Протестировать его нужно дважды: На возврат `true` и `false` при передаче ему разных значений. Значения устанавливаем с помощью функционала `Mockito`. 
То есть наша задача "замокать" обращение класса `UserService` к классу `UserDaoImpl`.

> Подсказки
> * Для проверок в `UserDaoTest` используйте методы `assertNull()`, `assertNotNull()` и` assertEquals()`;
> * Для проверок в `UserServiceTest` используйте метод `when()`; 
> * В конструкторе класса `UserServiceTest` используйте метод `MockitoAnnotations.initMocks(this)`;
> * Используйте в pom.xml следующие зависимости:
> 
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>test</scope>
          </dependency>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
          </dependency>
              <dependency>
                  <groupId>org.junit.jupiter</groupId>
                  <artifactId>junit-jupiter-engine</artifactId>
                  <version>5.8.1</version>
                  <scope>test</scope>
              </dependency>
              <dependency>
                  <groupId>org.mockito</groupId>
                  <artifactId>mockito-core</artifactId>
                  <version>2.21.0</version>
                  <scope>test</scope>
              </dependency>
              <dependency>
                  <groupId>org.mockito</groupId>
                  <artifactId>mockito-junit-jupiter</artifactId>
                  <version>2.23.0</version>
                  <scope>test</scope>
          </dependency>
      </dependencies>

<aside>
💡  Критерии оценки:
</aside>

* Корректно создан класс User: настроены модификаторы доступа, созданы геттеры и сеттеры
* Слои Dao и Service созданы корректно с соблюдением инструкций из условия задачи
* Созданные тесты корректно отрабатывают и проверяют то, что необходимо по условию

