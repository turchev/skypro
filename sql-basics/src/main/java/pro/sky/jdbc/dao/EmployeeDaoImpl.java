package pro.sky.jdbc.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.jdbc.exception.ConnectionException;
import pro.sky.jdbc.exception.DaoException;
import pro.sky.jdbc.model.City;
import pro.sky.jdbc.model.Employee;
import pro.sky.jdbc.service.DbConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final DbConnectionService dbConnectionService;

    @Override
    public int create(Employee employee) {
        final String query = "INSERT INTO employee (first_name, last_name, age, gender, city_id) " +
                "VALUES ((?), (?), (?), (?), (?))";
        try (PreparedStatement statement = dbConnectionService.getConnection().prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getGender());
            if (employee.getCity() == null) {
                statement.setNull(5, java.sql.Types.BIGINT);
            } else {
                statement.setLong(5, employee.getCity().getCityId());
            }
            return statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DaoException("Ошибка при добавлении данных: " + e.getMessage());
        }
    }

    @Override
    public Employee findById(long id) {
        final String query = "SELECT * FROM employee e " +
                "INNER JOIN city c ON e.city_id = c.city_id WHERE id = (?)";
        try (PreparedStatement statement = dbConnectionService.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Employee employee = null;
            while (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));
            }
            return employee;
        } catch (SQLException | ConnectionException e) {
            throw new DaoException("Ошибка при поиске сущности по id: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        final String query = "SELECT * FROM employee e INNER JOIN city c ON e.city_id = c.city_id";
        try (PreparedStatement statement = dbConnectionService.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException | ConnectionException e) {
            throw new DaoException("Ошибка чтении всех записей: " + e.getMessage());
        }
    }

    @Override
    public void update(Employee employee, long id) {
        final String query = "UPDATE employee " +
                "SET first_name=(?), last_name=(?), age=(?), gender=(?), city_id=(?) " +
                "WHERE id=(?)";
        try (PreparedStatement statement = dbConnectionService.getConnection().prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getGender());
            statement.setLong(5, employee.getCity().getCityId());
            statement.setLong(6, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DaoException("Ошибка при изменении данных: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        final String query = "DELETE FROM employee WHERE id=(?)";
        try (PreparedStatement statement = dbConnectionService.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DaoException("Ошибка при удалении данных: " + e.getMessage());
        }
    }
}
