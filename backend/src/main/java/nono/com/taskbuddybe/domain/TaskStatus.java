package nono.com.taskbuddybe.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatus {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String status;

    @Override
    public String toString() {
        return this.status;
    }
}
