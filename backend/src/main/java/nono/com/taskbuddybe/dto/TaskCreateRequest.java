package nono.com.taskbuddybe.dto;

import jakarta.validation.constraints.*;
import nono.com.taskbuddybe.domain.Task;
import nono.com.taskbuddybe.domain.TaskPriority;
import nono.com.taskbuddybe.domain.TaskStatus;

import java.time.Instant;

public record TaskCreateRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,
        TaskPriority priority,
        @FutureOrPresent(message = "Due date cannot be in the past")
        Instant dueDate
) {

    public TaskCreateRequest {
        if (priority == null) {
            priority = TaskPriority.MEDIUM;
        }
    }

    public Task toDocument() {
        return Task.builder()
                .title(this.title)
                .description(this.description)
                .priority(this.priority)
                .dueDate(this.dueDate)
                .build();
    }

}
