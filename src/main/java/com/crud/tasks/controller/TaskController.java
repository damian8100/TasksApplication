package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @GetMapping
    public List<TaskDto>getTasks() {

        return new ArrayList<>();
    }

    @GetMapping
    public TaskDto getTask(long taskId) {

        return new TaskDto(1L, "test Title","test Content");
    }

    @DeleteMapping
    public void deleteTask(long taskId) {

    }

    @PutMapping
    public TaskDto updateTask(TaskDto task) {

        return new TaskDto(1L, "Edited test Title", "test Content");
    }

    @PostMapping
    public  void createTask(TaskDto task) {

    }
}
