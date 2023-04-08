package org.example.dao.memory;

import org.example.dao.TaskListDao;
import org.example.model.TaskList;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InMemoryTaskListDao implements TaskListDao {
    private static int INDEX_COUNT = 0;
    public static final Map<Long, TaskList> ID_TO_TASK_LIST_MAP = new HashMap<>();

    @Override
    public Optional<TaskList> findById(long id) {
        return Optional.ofNullable(ID_TO_TASK_LIST_MAP.get(id)).map(TaskList::copy);
    }

    @Override
    public List<TaskList> findAll() {
        return ID_TO_TASK_LIST_MAP.values().stream().map(TaskList::copy).collect(Collectors.toList());
    }

    @Override
    public void create(TaskList taskList) {
        final TaskList copy = taskList.copy();
        copy.setId(INDEX_COUNT);
        INDEX_COUNT++;
        ID_TO_TASK_LIST_MAP.put(copy.getId(), copy);
    }

    @Override
    public void deleteById(long id) {
        InMemoryTaskDao.deleteTaskListFromIndex(id);
        ID_TO_TASK_LIST_MAP.remove(id);
    }
}
