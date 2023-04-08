package org.example.controller;

import org.example.dao.TaskDao;
import org.example.dao.TaskListDao;
import org.example.dto.BoardDto;
import org.example.dto.TaskDto;
import org.example.model.TaskList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class IndexController {
    private final TaskDao taskDao;
    private final TaskListDao taskListDao;

    @GetMapping(value = "/")
    public ModelAndView view() {
        final ModelAndView modelAndView = new ModelAndView("Index");

        final List<TaskList> taskLists = taskListDao.findAll();
        final Map<Long, TaskList> idToTaskListMap = taskLists.stream().collect(toMap(TaskList::getId, identity()));
        modelAndView.addObject("taskLists", taskLists);

        final BoardDto board = taskDao.findAll().stream()
                .map(task -> new TaskDto(task, idToTaskListMap.get(task.getTaskListId())))
                .collect(collectingAndThen(toList(), BoardDto::new));
        modelAndView.addObject("board", board);

        return modelAndView;
    }
}
