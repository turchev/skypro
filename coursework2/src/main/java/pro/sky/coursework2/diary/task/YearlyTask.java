package pro.sky.coursework2.diary.task;

import pro.sky.coursework2.diary.exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(Type type, String title, String description, LocalDateTime dateTime)
            throws IncorrectArgumentException {
        super(type, title, description, dateTime);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }
        return getLocalDateTime().getDayOfYear() == localDate.getDayOfYear();
    }

    @Override
    public String toString() {
        return "Ежегодная " + super.toString();
    }
}
