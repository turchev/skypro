package pro.sky.jdbc.dao;

import java.util.List;

public interface Dao<T> {

    /**
     * Создает(добавляет) сущность T в таблицу
     *
     * @param entity сущность
     */
    void create(T entity);

    /**
     * Ищет в таблице сущность T по id
     *
     * @param id идентификатор (первичный ключ)
     * @return сущность T
     */
    T findById(Long id);

    /**
     * Извлекает список всех сущностей T из таблицы
     *
     * @return список всех объектов T
     */
    List<T> findAll();

    /**
     * Обновляет сущность T в базе
     *
     * @param entity сущность T
     */
    void update(T entity);

    /**
     * Удаляет сущность T в базе
     *
     * @param entity сущность T
     */
    void delete(T entity);
}
