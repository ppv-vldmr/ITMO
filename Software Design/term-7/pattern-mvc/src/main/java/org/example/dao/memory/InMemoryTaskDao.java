package org.example.dao.memory;

import org.example.dao.TaskDao;
import org.example.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryTaskDao implements TaskDao {
    private static int INDEX_COUNT = 0;
    private static final Map<Long, Task> ID_TO_TASK_MAP = new HashMap<>();
    private static final Map<Long, Set<Long>> TASK_LIST_ID_TO_ID_MAP = new HashMap<>();

    static void deleteTaskListFromIndex(long taskListId) {
        final Set<Long> taskIds = TASK_LIST_ID_TO_ID_MAP.getOrDefault(taskListId, Collections.emptySet());
        taskIds.forEach(taskId -> ID_TO_TASK_MAP.get(taskId).setTaskListId(null));
        TASK_LIST_ID_TO_ID_MAP.remove(taskListId);
    }

    private final InMemoryTaskListDao taskListDao;

    @Override
    public Optional<Task> findById(long id) {
        return Optional.ofNullable(ID_TO_TASK_MAP.get(id)).map(Task::copy);
    }

    @Override
    public List<Task> findAll() {
        return ID_TO_TASK_MAP.values().stream().map(Task::copy).collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllByTaskListId(long taskListId) {
        return TASK_LIST_ID_TO_ID_MAP.getOrDefault(taskListId, Collections.emptySet()).stream()
                .map(ID_TO_TASK_MAP::get)
                .map(Task::copy)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Task task) {
        final Task copy = task.copy();
        copy.setId(INDEX_COUNT);
        INDEX_COUNT++;
        indexTaskList(copy);
        ID_TO_TASK_MAP.put(copy.getId(), copy);
    }

    @Override
    public void save(Task task) {
        final Task copy = task.copy();
        deleteTaskListFromTask(task);
        indexTaskList(copy);
        ID_TO_TASK_MAP.put(copy.getId(), copy);
    }

    @Override
    public void deleteById(long id) {
        findById(id).ifPresent(this::deleteTaskListFromTask);
        ID_TO_TASK_MAP.remove(id);
    }

    private void deleteTaskListFromTask(Task task) {
        if (task.getTaskListId() != null) {
            TASK_LIST_ID_TO_ID_MAP.getOrDefault(task.getTaskListId(), Collections.emptySet()).remove(task.getId());
        }
    }

    private void indexTaskList(Task task) {
        if (task.getTaskListId() != null && taskListDao.findById(task.getTaskListId()).isEmpty()) {
            task.setTaskListId(null);
        } else if (task.getTaskListId() != null) {
            TASK_LIST_ID_TO_ID_MAP.putIfAbsent(task.getTaskListId(), new HashSet<>());
            TASK_LIST_ID_TO_ID_MAP.get(task.getTaskListId()).add(task.getId());
        }
    }
}
