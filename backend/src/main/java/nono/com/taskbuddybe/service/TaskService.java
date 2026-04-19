package nono.com.taskbuddybe.service;

import nono.com.taskbuddybe.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAllTasks();
    Optional<Task> findOneTaskById(String id);
    Task createNewTask(Task task);
    Task updateTaskStatus(String id, nono.com.taskbuddybe.domain.TaskStatus status);
    void deleteTask(String id);
    void clearCompletedTasks();
}
