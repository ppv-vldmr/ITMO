package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Copyable<Task> {
    private long id;
    private String name;
    private String description;
    private Status status;
    private Long taskListId;
    private Date creationDate;

    @Override
    public Task copy() {
        return new Task(id, name, description, status, taskListId, new Date(creationDate.getTime()));
    }
}
