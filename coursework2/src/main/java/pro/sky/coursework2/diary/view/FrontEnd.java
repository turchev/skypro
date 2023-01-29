package pro.sky.coursework2.diary.view;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.coursework2.diary.exception.IncorrectArgumentException;
import pro.sky.coursework2.diary.service.TaskService;
import pro.sky.coursework2.diary.task.*;
import pro.sky.coursework2.diary.view.scanned.FrontEndService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

/**
 * Консольный интерфейс пользователя
 */
@Log4j2
@Component(FrontEnd.NAME)
public class FrontEnd {
    static final String NAME = "diary_FrontEnd";

    @Autowired
    private TaskService taskService;
    @Autowired
    private FrontEndService frontEndService;

    @PostConstruct
    private void init() throws IncorrectArgumentException {
        // добавление разнотипных задач, с разной повторяемостью для массовки, чтобы меньше мучатся в интерактиве:)
        taskService.addTask(new OneTimeTask(Type.PERSONAL, "Title", "Description", LocalDateTime.now()));
        taskService.addTask(new YearlyTask(Type.WORK, "Title2", "Description2", LocalDateTime.now()));
        taskService.addTask(new MonthlyTask(Type.PERSONAL, "Title3", "Description3", LocalDateTime.now()));
        taskService.addTask(new WeeklyTask(Type.WORK, "Title4", "Description4", LocalDateTime.now()));
        taskService.addTask(new DailyTask(Type.PERSONAL, "Title5", "Description5", LocalDateTime.now()));
    }

    public void run() {
        boolean flag = true;
        do {
            try {
                flag = chooseActionAndContinue();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } while (flag);
    }

    private boolean chooseActionAndContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nВыберите действие: \n\t0 - Выйти\n\t1 - Добавить задачу" +
                "\n\t2 - Получить задачи на день\n\t3 - Удалить задачу по id");
        try {
            if (!sc.hasNextInt()) {
                System.out.println("Выберите корректные значения от 0 до 4");
                return true;
            }
            int flag = sc.nextInt();
            switch (flag) {
                case 0:
                    return false;
                case 1:
                    taskService.addTask(frontEndService.buildTask());
                    return true;
                case 2:
                    printTasks(taskService.getAllByDate(frontEndService.inLocalDate()));
                    return true;
                case 3:
                    taskService.remove(frontEndService.inTaskId());
                    return true;
                default:
                    System.out.println("Выберите корректные значения от 0 до 4");
                    return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Что-то пошло не так, полундра :) " + e.getMessage());
        }
    }

    private void printTasks(Collection<Task> tasks) {
        tasks.forEach(System.out::println);
    }
}
