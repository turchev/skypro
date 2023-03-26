package pro.sky.jdbc.dao;

import pro.sky.jdbc.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    /**
     * Создает(добавляет) сущность Employee в таблицу
     *
     * @param employee сущность Employee
     */
    void create(Employee employee);

    /**
     * Ищет в таблице сущность Employee по id
     *
     * @param id идентификатор (первичный ключ)
     * @return сущность Employee
     */
    Employee findById(Long id);

    /**
     * Извлекает список всех сущностей Employee из базы
     *
     * @return список всех объектов Employee
     */
    List<Employee> findAll();

    /**
     * Обновляет сущность Employee в базе
     *
     * @param employee сущность Employee
     */
    void update(Employee employee);

    /**
     * Удаляет сущность Employee в базе
     *
     * @param employee сущность Employee
     */
    void delete(Employee employee);
}
