package pro.sky.coursework2.diary.scanned;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component(LocalDateTimeComponent.NAME)
class LocalDateTimeComponent {
    static final String NAME = "scan_LocalDateTimeComponent";

    protected LocalDate createLocalDate() {
        LocalDate localDate;
        do {
            localDate = scanLocalDateFromStdin();
        } while (localDate == null);
        return localDate;
    }

    protected LocalTime createLocalTime() {
        LocalTime localTime;
        do {
            localTime = scanLocalTimeFromStdin();
        } while (localTime == null);
        return localTime;
    }

    private LocalDate scanLocalDateFromStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tВведите дату задачи в формате 'yyyy-MM-dd'");
        try {
            String nextLine = sc.nextLine();
            return LocalDate.parse(nextLine, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            System.out.println("Некорректный ввод даты");
            return null;
        }
    }

    private LocalTime scanLocalTimeFromStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\tВведите время задачи в формате 'HH:mm'");
        try {
            String nextLine = sc.nextLine();
            return LocalTime.parse(nextLine, DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (Exception e) {
            System.out.println("Не корректный ввод времени");
            return null;
        }
    }
}
