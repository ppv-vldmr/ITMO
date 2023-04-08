package org.example.dto;

import org.example.model.Status;
import org.example.model.Task;
import org.example.model.TaskList;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TaskDto {
    private final long id;
    private final String name;
    private final String description;
    private final Status status;
    private final TaskList taskList;
    private final Date creationDate;

    public TaskDto(Task task, TaskList taskList) {
        this(task.getId(), task.getName(), task.getDescription(), task.getStatus(), taskList, task.getCreationDate());
    }

    public boolean isTodo() {
        return status == Status.TO_DO;
    }

    public boolean isInProgress() {
        return status == Status.IN_PROGRESS;
    }

    public boolean isCompleted() {
        return status == Status.COMPLETED;
    }
}
