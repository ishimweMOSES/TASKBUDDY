package nono.com.taskbuddybe.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String priority;

    @Override
    public String toString() {
        return this.priority;
    }
}
