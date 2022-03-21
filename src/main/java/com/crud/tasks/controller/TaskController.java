package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/tasks")

public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/v1/tasks")
    public List<TaskDto>getTasks() {
        List<Task>tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable long taskId) {

        return new TaskDto(1L, "test Title","test Content");
    }

    @DeleteMapping("/v1/tasks")
    public void deleteTask(@PathVariable long taskId) {

    }

    @PutMapping(value = "/v1/tasks")
    public TaskDto updateTask(TaskDto task) {

        return new TaskDto(1L, "Edited test Title", "test Content");
    }

    @PostMapping(value = "(/v1/tasks", consumes ="title/content", produces = "title/content")
    public  void createTask(@RequestBody TaskDto task) {

    }
}
