package pro.sky.coursework2.diary.task;

import org.springframework.stereotype.Service;
import pro.sky.coursework2.diary.exception.IncorrectArgumentException;
import pro.sky.coursework2.diary.exception.TaskNotFoundException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service(TaskService.NAME)
class TaskComponent implements TaskService {
    private static final int DAYS_TO_ADD = 10;
    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Collection<Task> removedTasks = new ArrayList<>();

    @Override
    public void addTask(@Nonnull Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    @Nullable
    public Task remove(int id) throws TaskNotFoundException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Не найдена задача с id: " + id);
        }
        if (!removedTasks.add(taskMap.get(id))) {
            return null;
        }
        return taskMap.remove(id);
    }

    @Override
    public Collection<Task> getAllByDate(@Nonnull LocalDate localDate) {
        return taskMap.values().stream()
                .filter(Objects::nonNull)
                .filter(task -> task.appearsln(localDate))
                .collect(Collectors.toList());
    }

    @Override
    public Task updateTitle(int id, @Nonnull String title) throws TaskNotFoundException, IncorrectArgumentException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Не найдена задача с id: " + id);
        }
        Task task = taskMap.get(id);
        task.setTitle(title);
        return task;
    }

    @Override
    public Task updateDescription(int id, @Nonnull String description) throws TaskNotFoundException, IncorrectArgumentException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Не найдена задача с id: " + id);
        }
        Task task = taskMap.get(id);
        task.setDescription(description);
        return task;
    }

    @Override
    public Collection<Task> getRemovedTasks() {
        return this.removedTasks;
    }

    @Override
    public Map<LocalDate, Collection<Task>> getAllGroupeByDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.datesUntil(LocalDate.now().plusDays(DAYS_TO_ADD))
                .collect(Collectors.toMap(Function.identity(), this::getAllByDate,
                        (t1, t2) -> t1, LinkedHashMap::new));
    }
}
