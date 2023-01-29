package pro.sky.coursework2.diary.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework2.diary.exception.TaskNotFoundException;
import pro.sky.coursework2.diary.task.Task;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service(TaskService.NAME)
public class TaskServiceImpl implements TaskService {

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private Collection<Task> removedTasks;

    @Override
    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public Task remove(int id) throws TaskNotFoundException {
        if (!taskMap.containsKey(id)) {
            throw new TaskNotFoundException("Не найдена задача с id: " + id);
        }
        return taskMap.remove(id);
    }

    @Override
    public Collection<Task> getAllByDate(LocalDate localDate) {
        return taskMap.values().stream()
                .filter(Objects::nonNull)
                .filter(task -> task.appearsln(localDate))
                .collect(Collectors.toList());
    }
}
