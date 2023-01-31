package pro.sky.coursework2.diary.view;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.coursework2.diary.exception.IncorrectArgumentException;
import pro.sky.coursework2.diary.scanned.ScannedService;
import pro.sky.coursework2.diary.task.*;
import pro.sky.coursework2.diary.textparser.TextParserService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Консольный интерфейс пользователя
 */
@Log4j2
@Component(FrontEnd.NAME)
public class FrontEnd {
    static final String NAME = "diary_FrontEnd";

    private int flag = 0;

    @Autowired
    private TaskService taskService;
    @Autowired
    private ScannedService scannedService;
    @Autowired
    private TextParserService textParserService;

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
        System.out.println(Init.CHOOSE_ACTION_AND_CONTINUE);
        try {
            if (!sc.hasNextInt()) {
                System.out.println(Init.INPUT_RESTRICTION);
                return true;
            }
            int flag = sc.nextInt();
            switch (flag) {
                case 0:
                    return false;
                case 1:
                    taskService.addTask(scannedService.buildTask());
                    return true;
                case 2:
                    printTasks(taskService.getAllByDate(scannedService.inLocalDate()));
                    return true;
                case 3:
                    taskService.remove(scannedService.inTaskId());
                    return true;
                case 4:
                    printTasks(taskService.getRemovedTasks());
                    return true;
                case 5:
                    taskService.updateTitle(scannedService.inTaskId(),
                            scannedService.inText(Init.UPDATE_TITLE));
                    return true;
                case 6:
                    taskService.updateDescription(scannedService.inTaskId(),
                            scannedService.inText(Init.UPDATE_DESCRIPTION));
                    return true;
                case 7:
                    printTasks(taskService.getAllGroupeByDate());
                    return true;
                case 8:
                    printResultTextParsing(textParserService.countNumberWordMatchesInText(
                            scannedService.inText(Init.TEXT_ANALYSIS_TASK),
                            Init.STRING_MIN_LENGTH));
                    return true;
                default:
                    System.out.println(Init.INPUT_RESTRICTION);
                    return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Что-то пошло не так, полундра :) " + e.getMessage());
        }
    }

    private void printTasks(Collection<Task> tasks) {
        tasks.forEach(System.out::println);
    }

    private void printTasks(Map<LocalDate, Collection<Task>> taskMap) {
        taskMap.forEach((localDate, tasks) -> {
            if (tasks.isEmpty()) {
                System.out.println("Нет задач на " + localDate);
            } else {
                System.out.println("Задачи на " + localDate);
                tasks.forEach(task -> {
                    System.out.println("\t" + task);
                });
            }
        });
    }

    private void printResultTextParsing(TreeMap<Integer, HashSet<String>> numberWordMatchesInText) {
        int wordSum = 0;
        StringBuilder stringBuilder = new StringBuilder("TOP" + Init.TOP_NUMBER + ":\n");
        for (Integer i : numberWordMatchesInText.keySet()) {
            wordSum = wordSum + (i * numberWordMatchesInText.get(i).size());
            numberWordMatchesInText.get(i).stream()
                    .sorted()
                    .forEach(s -> {
                        if (flag <= Init.TOP_NUMBER) {
                            stringBuilder.append(i).append(" - ").append(s).append("\n");
                        }
                        flag++;
                    });
        }
        System.out.println("В тексте " + wordSum + " слов");
        System.out.println(stringBuilder);
    }

    public static final class Init {
        private static final String CHOOSE_ACTION_AND_CONTINUE = "\n\nВыберите действие: \n\t0 - Выйти\n\t1 - Добавить задачу\n\t2 - Получить задачи на день" +
                "\n\t3 - Удалить задачу по id\n\t4 - Получить список всех удаленных задач\n\t5 - Изменить заголовок задачи по id" +
                "\n\t6 - Изменить описание задачи по id\n\t7 - Получать задачи, сгруппированные по датам (на 10 дней)" +
                "\n\t8 - Задача на функциональное программирование";
        private static final String INPUT_RESTRICTION = "Выберите корректные значения от 0 до 8";
        private static final String UPDATE_TITLE = "Новый заголовок:";
        private static final String UPDATE_DESCRIPTION = "Новое описание:";
        private static final String TEXT_ANALYSIS_TASK = "Срочно введите произвольный набор слов и на выходе получите " +
                "TOP10 самых часто упоминаемых слов, упорядоченных по количеству упоминаний " +
                "в обратном порядке практически бесплатно";
        private static final int TOP_NUMBER = 10;
        private static final int STRING_MIN_LENGTH = 1;
    }
}