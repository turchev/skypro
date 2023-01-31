package pro.sky.coursework2.diary.scanned;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.coursework2.diary.task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

@Log4j2
@Component(TaskBuilderComponent.NAME)
class TaskBuilderComponent {
    static final String NAME = "scan_TaskBuilderComponent";
    private static final int MAX_ERROR_COUNT = 2;

    @Autowired
    private TypeComponent typeComponent;
    @Autowired
    private LocalDateTimeComponent localDateTimeComponent;
    @Autowired
    private TextComponent textComponent;

    protected Task buildTask() {
        System.out.println("Создание задачи в интерактивном режиме." +
                "\nОбработка значений упрощенная, старайтесь вводить точно следуя инструкции.");

        Type type = typeComponent.createType();
        String title = textComponent.createText("Заголовок");
        String description = textComponent.createText(" Описание");
        LocalDate localDate = localDateTimeComponent.createLocalDate();
        LocalTime localTime = localDateTimeComponent.createLocalTime();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        Task task = null;
        int errCount = 0;
        do {
            try {
                task = selectRepetitionsAndCreateTask(type, title, description, localDateTime);
            } catch (Exception e) {
                log.error(e.getMessage());
                errCount++;
            }
        } while (task == null && errCount < MAX_ERROR_COUNT);

        return task;
    }

    private Task selectRepetitionsAndCreateTask(Type type, String title, String description, LocalDateTime localDateTime) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nВыберите повторяемость задачи \n\t1 - однократная,\n\t2 - ежедневная," +
                "\n\t3 - еженедельная,\n\t4 - ежемесячная,\n\t5 -  ежегодная");
        try {
            int inputValue = sc.nextInt();
            switch (inputValue) {
                case 1:
                    return new OneTimeTask(type, title, description, localDateTime);
                case 2:
                    return new DailyTask(type, title, description, localDateTime);
                case 3:
                    return new WeeklyTask(type, title, description, localDateTime);
                case 4:
                    return new MonthlyTask(type, title, description, localDateTime);
                case 5:
                    return new YearlyTask(type, title, description, localDateTime);
                default:
                    System.out.println("Выберите корректное значение от 1 до 5");
                    return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания задачи");
        }
    }
}
