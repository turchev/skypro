package pro.sky.coursework2.diary.task;

import pro.sky.coursework2.diary.exception.IncorrectArgumentException;
import pro.sky.coursework2.diary.exception.TaskNotFoundException;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public interface TaskService {
    String NAME = "diary_TaskService";

    /**
     * Добавляет задачу.
     *
     * @param task Задача.
     */
    void addTask(@Nonnull Task task);

    /**
     * Удаляет задачу по id.
     *
     * @param id Идентификатор задачи.
     * @returns {@link Task} Возвращает найденную удаляемую задачу или null Если задача с таким id найдена,
     * но по каким-либо причинам не переместилась в архив
     * @throws TaskNotFoundException В случае если задача с таким id не найдена.
     */
    Task remove(int id) throws TaskNotFoundException;

    /**
     * Получает задачи на дату.
     *
     * @param localDate Неизменяемый объект даты и времени, который представляет дату, часто рассматриваемую как год-месяц-день.
     * @return Возвращает коллекцию задач
     */
    Collection<Task> getAllByDate(@Nonnull LocalDate localDate);

    /**
     * Обновляет заголовок в задаче, выбранной по id
     *
     * @param id    Идентификатор задачи, в которой нужно обновить описание
     * @param title Заголовок
     * @return {@link Task} Возвращает обновленную задачу
     */
    Task updateTitle(int id, String title) throws TaskNotFoundException, IncorrectArgumentException;

    /**
     * Обновляет описание в задаче, выбранной по id
     *
     * @param id          Идентификатор задачи, в которой нужно обновить описание
     * @param description Описание
     * @return {@link Task} Возвращает обновленную задачу
     */
    Task updateDescription(int id, @Nonnull String description) throws TaskNotFoundException, IncorrectArgumentException;

    /**
     * Получает список всех удаленных задач.
     *
     * @return {@link Collection<Task>} Возвращает коллекцию задач из архива
     */
    Collection<Task> getRemovedTasks();

    /**
     * Получает задачи, сгруппированные по датам.
     *
     * @return Возвращает карту, где ключ - дата, значение - коллекция задач в этот день
     */
    Map<LocalDate, Collection<Task>> getAllGroupeByDate();

}
