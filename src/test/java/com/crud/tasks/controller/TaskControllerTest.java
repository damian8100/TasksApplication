package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class) // w przypadku tego zapisu nie dajemy w kodzie adnotacji @InjectMoks
class TaskControllerTest {


        @Autowired
        private MockMvc mockMvc;


        @MockBean // w takich przypadkach lepij korzystać z adnotacji @Mock
        private DbService dbService;

        @MockBean
        TaskMapper taskMapper;


    @Test
    void shouldGetTask() throws Exception {

        // Given
        Task task = new Task(1L,"tytul1","test");
        TaskDto taskDto = new TaskDto(1L,"tytul1","test");

        when(dbService.getSingleTask(1L)).thenReturn(task);

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(status().is(200));
    }

    @Test
    void shouldGetTasks() throws Exception {

        //Given
        Task task = new Task(1L,"tytul1","test");
        TaskDto taskDto = new TaskDto(1L,"tytul1","test");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title",Matchers.is("tytul1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content",Matchers.is("test")))
                .andExpect(status().is(200));
    }
    @Test
    void shouldDeleteTask() throws Exception {
//Given

        Task task = new Task(1L,"tytul1","test");
        TaskDto taskDto = new TaskDto(1L,"tytul1","test");

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

//When
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());


    }

    @Test
    void shouldUpdateTask() throws Exception {

        Task task = new Task(1L,"tytul1","test");
        TaskDto taskDto = new TaskDto(1L,"tytul1","test");

//Given
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

//When & Then

        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title",Matchers.is("tytul1")))
                .andExpect(jsonPath("$.content",Matchers.is("test")));

    }


    @Test
    void createTask() throws Exception {

        Task task = new Task(1L,"tytul1","test");
        TaskDto taskDto = new TaskDto(1L,"tytul1","test");


//Given
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

//When & Then

        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

    }


}


