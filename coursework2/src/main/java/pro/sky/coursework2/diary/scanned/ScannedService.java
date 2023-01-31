package pro.sky.coursework2.diary.scanned;

import pro.sky.coursework2.diary.task.Task;

import javax.annotation.Nonnull;
import java.time.LocalDate;

public interface ScannedService {
    String NAME = "diary_FrontEndService";

    /**
     * Запускает в интерактивном режиме процесс создания задачи.
     * Последовательно в консольном режиме запрашивает данные для всех параметров задачи и создает ее.
     *
     * @return {@link Task} Возвращает задачу, созданную из введенных пользователем параметров
     */
    Task buildTask();

    /**
     * Запускает в интерактивном режиме процесс создания даты.
     *
     * @return {@link LocalDate} Возвращает дату, созданную из введенных пользователем значений
     */
    LocalDate inLocalDate();

    /**
     * Запускает в интерактивном режиме процесс создания времени.
     *
     * @return  Возвращает id (int), созданное из введенного пользователем значения
     */
    int inTaskId();

    /**
     * Запускает в интерактивном режиме процесс создания текста.
     *
     * @param textType Будет выведено в диалоге
     * @return {@link String} Возвращает строку, созданную из введенных пользователем значений
     */
    String inText(@Nonnull String textType);
}
