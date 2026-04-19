package nono.com.taskbuddybe.service;

import lombok.RequiredArgsConstructor;
import nono.com.taskbuddybe.domain.Task;
import nono.com.taskbuddybe.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findOneTaskById(String id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskStatus(String id, nono.com.taskbuddybe.domain.TaskStatus status) {
        Optional<Task> taskOp = taskRepository.findById(id);
        if (taskOp.isPresent()) {
            Task task = taskOp.get();
            task.setStatus(status);
            return taskRepository.save(task);
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void clearCompletedTasks() {
        List<Task> completed = taskRepository.findAll().stream()
                .filter(t -> t.getStatus() == nono.com.taskbuddybe.domain.TaskStatus.DONE)
                .toList();
        taskRepository.deleteAll(completed);
    }
}
