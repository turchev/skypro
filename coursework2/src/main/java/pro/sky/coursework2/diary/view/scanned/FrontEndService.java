package pro.sky.coursework2.diary.view.scanned;

import pro.sky.coursework2.diary.task.Task;

import java.time.LocalDate;

public interface FrontEndService {
    String NAME = "diary_FrontEndService";

    /**
     * Запускает в интерактивном режиме процесс создания задачи.
     * Последовательно в консольном режиме запрашивает данные для всех параметров задачи и создает ее.     *
     *
     * @return Task
     */
    Task buildTask();

    /**
     * Запускает в интерактивном режиме процесс создания даты.
     *
     * @return LocalDate
     */
    LocalDate inLocalDate();

    /**
     * Запускает в интерактивном режиме процесс создания времени.
     *
     * @return int
     */
    int inTaskId();
}
