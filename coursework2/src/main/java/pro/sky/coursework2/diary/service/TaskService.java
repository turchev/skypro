package pro.sky.coursework2.diary.service;

import pro.sky.coursework2.diary.exception.TaskNotFoundException;
import pro.sky.coursework2.diary.task.Task;

import java.time.LocalDate;
import java.util.Collection;

public interface TaskService {
    String NAME = "diary_TaskService";

    void addTask(Task task);

    Task remove(int id) throws TaskNotFoundException;

    Collection<Task> getAllByDate(LocalDate localDate);
}
