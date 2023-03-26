package pro.sky.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.jdbc.dao.EmployeeDao;
import pro.sky.jdbc.entity.Employee;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Application {
    private static final long EMPLOYEE_ID = 10L;
    private final EmployeeDao employeeDao;

    @PostConstruct
    private void init() {

        // Создание(добавление) сущности Employee в таблицу
        final int samara = 5;
        final Employee createdEmployee = new Employee("Владимир", "Ленин", "М", 53, samara);
        employeeDao.create(createdEmployee);

        // Получение конкретного объекта Employee по id
        System.out.println("Пользователь: " + employeeDao.findById(EMPLOYEE_ID));

        // Изменение конкретного объекта Employee в базе по id
        final int moskow = 2;
        final Employee updatedEmployee = new Employee("Нестор", "Махно", "М", 45, moskow);
        updatedEmployee.setId(EMPLOYEE_ID);
        employeeDao.update(updatedEmployee);

        // Получение списка всех объектов Employee из базы и вывод в консоль
        employeeDao.findAll().forEach(System.out::println);

        // Удаление конкретного объекта Employee из базы по id
        employeeDao.delete(updatedEmployee);
    }
}