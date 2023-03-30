package pro.sky.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.jdbc.dao.Dao;
import pro.sky.jdbc.entity.City;
import pro.sky.jdbc.entity.Employee;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Application {
    private static final long EMPLOYEE_ID = 5L;
    private static final long CITY_ID = 5L;
    private final Dao<Employee> employeeDao;
    private final Dao<City> cityDao;

    @PostConstruct
    private void init() {

        // Создание(добавление) связанных сущностей в таблицы
        Employee createdEmployee = new Employee("Владимир", "Ленин", "М", 53, new City("Магадан"));
        employeeDao.create(createdEmployee);

        City city = new City("Краснодар");
        city.setEmployees(List.of(new Employee("Марк", "Твен", "М", 77), new Employee("Федор", "Достоевский", "М", 55)));
        cityDao.create(city);

        // Получение конкретного объекта по id
        Employee foundEmployee = employeeDao.findById(EMPLOYEE_ID);
        System.out.println("Пользователь: " + foundEmployee);
        City foundCity = cityDao.findById(CITY_ID);
        System.out.println("Город: " + foundCity);

        // Изменение конкретного объекта в базе по id
        foundEmployee.setAge(100);
        employeeDao.update(foundEmployee);
        foundCity.setCityName("Уфа");
        cityDao.update(foundCity);

        // Получение списка всех объектов Employee из базы и вывод в консоль
        employeeDao.findAll().forEach(System.out::println);
        cityDao.findAll().forEach(System.out::println);

        // Удаление конкретного объекта из базы
        employeeDao.delete(foundEmployee);
        cityDao.delete(foundCity);
    }
}