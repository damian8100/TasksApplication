package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @GetMapping(value = {"taskId"})
    public TaskDto getSingleTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                service.getSingleTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);

    }
}
