package pro.sky.coursework2.diary.task;

import pro.sky.coursework2.diary.exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(Type type, String title, String description, LocalDateTime dateTime)
            throws IncorrectArgumentException {
        super(type, title, description, dateTime);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        return localDate != null;
    }

    @Override
    public String toString() {
        return "Ежедневная " + super.toString();
    }
}
