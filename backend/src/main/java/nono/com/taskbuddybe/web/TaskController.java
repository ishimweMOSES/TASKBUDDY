package nono.com.taskbuddybe.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nono.com.taskbuddybe.domain.Task;
import nono.com.taskbuddybe.dto.TaskCreateRequest;
import nono.com.taskbuddybe.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://taskbuddy-git-main-ishimwemoses-projects.vercel.app",
    "https://taskbuddy.vercel.app"
})
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createNewTask(@Valid @RequestBody TaskCreateRequest request) {
        Task task = taskService.createNewTask(request.toDocument());
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getSingleTask(@PathVariable String id) {
        Optional<Task> taskOp = taskService.findOneTaskById(id);
        return taskOp.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable String id, @RequestBody java.util.Map<String, String> body) {
        nono.com.taskbuddybe.domain.TaskStatus status = nono.com.taskbuddybe.domain.TaskStatus.valueOf(body.get("status").toUpperCase());
        return ResponseEntity.ok(taskService.updateTaskStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/completed/clear")
    public ResponseEntity<Void> clearCompletedTasks() {
        taskService.clearCompletedTasks();
        return ResponseEntity.noContent().build();
    }

}
