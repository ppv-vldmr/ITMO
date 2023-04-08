package org.example.dto;

import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class BoardDto {
    private final List<TaskDto> todoTasks;
    private final List<TaskDto> inProgressTasks;
    private final List<TaskDto> completedTasks;

    public BoardDto(List<TaskDto> tasks) {
        this.todoTasks = tasks.stream().filter(TaskDto::isTodo).collect(toList());
        this.inProgressTasks = tasks.stream().filter(TaskDto::isInProgress).collect(toList());
        this.completedTasks = tasks.stream().filter(TaskDto::isCompleted).collect(toList());
    }
}
