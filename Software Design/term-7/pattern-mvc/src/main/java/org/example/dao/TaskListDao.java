package org.example.dao;

import org.example.model.TaskList;

import java.util.List;
import java.util.Optional;

public interface TaskListDao {
    Optional<TaskList> findById(long id);
    List<TaskList> findAll();
    void create(TaskList taskList);
    void deleteById(long id);
}
