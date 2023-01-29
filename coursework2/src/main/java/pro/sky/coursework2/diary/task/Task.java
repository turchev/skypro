package pro.sky.coursework2.diary.task;

import lombok.Getter;
import pro.sky.coursework2.diary.exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator;
    @Getter
    private final int id;
    @Getter
    private final Type type;
    @Getter
    private String title;
    @Getter
    private final LocalDateTime localDateTime;
    @Getter
    private String description;

    public Task(Type type, String title, String description, LocalDateTime localDateTime)
            throws IncorrectArgumentException {
        if (type == null) {
            throw new IncorrectArgumentException("Требуется выбрать тип задачи", "type");
        }
        this.type = type;
        setTitle(title);
        setDescription(description);
        if (localDateTime == null) {
            throw new IncorrectArgumentException("Требуется дата и время задачи", "dateTime");
        }
        this.localDateTime = localDateTime;
        this.id = ++idGenerator;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title == null || title.isBlank()) {
            throw new IncorrectArgumentException("Требуется ввести заголовок задачи", "title");
        }
        this.title = title;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description == null || description.isBlank()) {
            throw new IncorrectArgumentException("Требуется описание задачи", "description");
        }
        this.description = description;
    }

    abstract public boolean appearsln(LocalDate localDate);

    @Override
    public String toString() {
        return "Задача {id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", dateTime=" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                ", description='" + description + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return getId() == task.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
