package nono.com.taskbuddybe.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;

    @Builder.Default
    private TaskPriority priority = TaskPriority.MEDIUM;

    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @Indexed
    private Instant dueDate;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Transient
    public boolean isOverdue() {
        return dueDate != null
                && status != TaskStatus.DONE
                && Instant.now().isAfter(dueDate);
    }
}
