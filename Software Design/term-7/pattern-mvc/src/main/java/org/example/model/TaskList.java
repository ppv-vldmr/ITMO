package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskList implements Copyable<TaskList> {
    private long id;
    private String name;
    private Date creationDate;

    @Override
    public TaskList copy() {
        return new TaskList(id, name, creationDate);
    }
}
