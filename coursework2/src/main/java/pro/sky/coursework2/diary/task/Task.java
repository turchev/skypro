package pro.sky.coursework2.diary.task;

import lombok.Getter;
import pro.sky.coursework2.diary.exception.IncorrectArgumentException;

import javax.annotation.Nonnull;
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

    public Task(@Nonnull Type type, @Nonnull String title, @Nonnull String description,
                @Nonnull LocalDateTime localDateTime) throws IncorrectArgumentException {
        this.type = type;
        setTitle(title);
        setDescription(description);
        this.localDateTime = localDateTime;
        this.id = ++idGenerator;
    }

    public void setTitle(@Nonnull String title) throws IncorrectArgumentException {
        if (title.isBlank()) {
            throw new IncorrectArgumentException("Требуется ввести заголовок задачи", "title");
        }
        this.title = title;
    }

    public void setDescription(@Nonnull String description) throws IncorrectArgumentException {
        if (description.isBlank()) {
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
