package pro.sky.coursework2.diary.task;

import pro.sky.coursework2.diary.exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {

    public OneTimeTask(Type type, String title, String description, LocalDateTime dateTime)
            throws IncorrectArgumentException {
        super(type, title, description, dateTime);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }
        return getLocalDateTime().toLocalDate().isEqual(localDate);
    }

    @Override
    public String toString() {
        return "Разовая " + super.toString();
    }
}
