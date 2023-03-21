package pro.sky.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import pro.sky.jdbc.dao.EmployeeDao;
import pro.sky.jdbc.exception.ConnectionException;
import pro.sky.jdbc.model.City;
import pro.sky.jdbc.model.Employee;
import pro.sky.jdbc.service.DbConnectionService;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@Component
@RequiredArgsConstructor
public class Application {
    private static final int EMPLOYEE_ID = 1;
    private static final int DELETED_EMPLOYEE_ID = 8;
    private final DbConnectionService dbConnectionService;
    private final EmployeeDao employeeDao;

    @PostConstruct
    private void init() {
        // Информация о соединении
        System.out.println(dbConnectionService);

        // вывод в консоль данных об одном из работников (имя, фамилия, пол, город) по id.
        printFoundEmployeeById(EMPLOYEE_ID);

        // Создание(добавление) сущности Employee в таблицу
        final City samara = new City(5, "Самара");
        final Employee createdEmployee = new Employee("Фёдор", "Тютчев", "М", 69, samara);
        createEmployeeAndPrintResult(createdEmployee);

        // Получение конкретного объекта Employee по id
        findEmployeeByIdAndPrintResult(EMPLOYEE_ID);

        // Получение списка всех объектов Employee из базы и вывод в консоль
        employeeDao.findAll().forEach(System.out::println);

        // Изменение конкретного объекта Employee в базе по id
        final City moskow = new City(2, "Москва");
        final Employee updatedEmployee = new Employee("Нестор", "Махно", "М", 45, moskow);
        employeeDao.update(updatedEmployee, EMPLOYEE_ID);

        // Удаление конкретного объекта Employee из базы по id
        employeeDao.delete(DELETED_EMPLOYEE_ID);
    }

    private void printFoundEmployeeById(int id) {
        String query = "SELECT e.first_name e_fn, e.last_name e_ln, e.gender e_g, c.city_name c_cn " +
                "FROM employee e INNER JOIN city c ON e.city_id = c.city_id WHERE id = (?)";
        try (PreparedStatement statement = dbConnectionService.getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("e_fn");
                String lastName = resultSet.getString("e_ln");
                String gender = resultSet.getString("e_g");
                String cityName = resultSet.getString("c_cn");

                System.out.printf("имя: %s, фамилия: %s, пол: %s, город: %s%n",
                        firstName, lastName, gender, cityName);
            }
        } catch (SQLException | ConnectionException e) {
            log.error("Ошибка получения данных: " + e.getMessage());
        }
    }

    private void createEmployeeAndPrintResult(Employee employee) {
        int row = employeeDao.create(employee);
        if (row > 0) {
            System.out.println("Сущность добавлена, количество строк таблицы: " + row);
        }
    }

    private void findEmployeeByIdAndPrintResult(long id) {
        Employee employee = employeeDao.findById(id);
        if (employee != null) {
            System.out.printf("По id=%d найден пользователь %s %s из города %s%n",
                    id, employee.getFirstName(), employee.getLastName(), employee.getCity());
        } else {
            System.out.printf("По id=%d пользователь не найден%n", id);
        }
    }
}