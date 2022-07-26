package com.crud.tasks.dbservicetest;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;



    @Test
    void dbServiceTest() throws Exception {

        //given
        List<Task>taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test1", "doooo"));
        taskList.add(new Task(2L,"test2", "ddddd"));

        //when

        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> result = dbService.getAllTasks();


        //then
        Assertions.assertEquals(2,result.size());

    }
}
