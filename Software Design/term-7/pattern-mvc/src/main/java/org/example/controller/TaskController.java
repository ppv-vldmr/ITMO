package org.example.controller;

import org.example.dao.TaskDao;
import org.example.dao.TaskListDao;
import org.example.dto.TaskDto;
import org.example.model.Status;
import org.example.model.Task;
import org.example.model.TaskList;
import org.example.util.ParseUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

import static org.example.model.Status.*;

@Controller
@RequestMapping(value = "/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskDao taskDao;
    private final TaskListDao taskListDao;

    @GetMapping(value = "/{id}")
    public ModelAndView view(@PathVariable("id") String id) {
        final long taskId = ParseUtils.parseLong(id);

        final ModelAndView modelAndView = new ModelAndView("Task");
        taskDao.findById(taskId).ifPresent(task -> {
            final Long taskListId = task.getTaskListId();
            final TaskList taskList = Optional.ofNullable(taskListId).flatMap(taskListDao::findById).orElse(null);
            modelAndView.addObject("task", new TaskDto(task, taskList));
        });
        return modelAndView;
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> create(@ModelAttribute Task formData) {
        if (Strings.isBlank(formData.getName())) {
            return ResponseEntity.badRequest().body("Task name required");
        } else if (Strings.isBlank(formData.getDescription())) {
            return ResponseEntity.badRequest().body("Task description required");
        }
        final Task task = new Task(-1, formData.getName(), formData.getDescription(), TO_DO, null, new Date());
        taskDao.create(task);

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        final long taskId = ParseUtils.parseLong(id);
        taskDao.deleteById(taskId);
        return ResponseEntity.ok("Task deleted");
    }

    @PostMapping(value = "/{id}/mark")
    public ResponseEntity<String> mark(@PathVariable("id") String id, @RequestParam("status") String status) {
        final long taskId = ParseUtils.parseLong(id);
        final Optional<Task> task = taskDao.findById(taskId);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final Status taskStatus = ParseUtils.parseEnum(status, Status.class);
        if (taskStatus == null) {
            return ResponseEntity.badRequest().body("Unknown status");
        }

        final Task updatedTask = task.get();
        if (updatedTask.getStatus() != taskStatus) {
            updatedTask.setStatus(taskStatus);
            taskDao.save(updatedTask);
        }

        return ResponseEntity.ok("Set task status to " + taskStatus);
    }
}
