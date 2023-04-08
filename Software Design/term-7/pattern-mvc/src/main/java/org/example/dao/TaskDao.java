package org.example.dao;

import org.example.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    Optional<Task> findById(long id);
    List<Task> findAll();
    List<Task> findAllByTaskListId(long taskListId);
    void create(Task task);
    void save(Task task);
    void deleteById(long id);
}
