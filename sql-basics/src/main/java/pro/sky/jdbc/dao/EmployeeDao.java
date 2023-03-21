package pro.sky.jdbc.dao;

import pro.sky.jdbc.model.Employee;

import java.util.List;

public interface EmployeeDao {

    /**
     * Создает(добавляет) сущность Employee в таблицу
     *
     * @param employee сущность Employee
     * @return количество строк в таблице или 0, если запись не добавлена
     */
    int create(Employee employee);

    /**
     * Ищет в таблице сущность Employee по id
     *
     * @param id идентификатор (первичный ключ)
     * @return сущность Employee
     */
    Employee findById(long id);

    /**
     * Извлекает список всех сущностей Employee из базы
     *
     * @return список всех объектов Employee
     */
    List<Employee> findAll();

    /**
     * Обновляет сущность Employee в базе по id
     *
     * @param employee сущность Employee
     * @param id       идентификатор обновляемой сущности
     */
    void update(Employee employee, long id);

    /**
     * Удаляет сущность Employee в базе по id
     *
     * @param id идентификатор удаляемой сущности
     */
    void delete(long id);
}
