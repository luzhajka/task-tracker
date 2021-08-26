package com.luzhajka.tasktracker;

import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.TaskService;
import com.luzhajka.tasktracker.service.impl.TaskServiceImpl;
import com.luzhajka.tasktracker.utils.CreateTaskDtoEntityMapper;
import com.luzhajka.tasktracker.utils.CreateTaskDtoEntityMapperImpl;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapper;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapperImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TaskServiceTest {

    @TestConfiguration
    static class TaskServiceTestContextConfiguration {

        @Bean
        public TaskService taskService(TaskRepository taskRepository, TaskDtoEntityMapper mapper, CreateTaskDtoEntityMapper createMapper) {
            return new TaskServiceImpl(taskRepository, mapper, createMapper);
        }

        @Bean
        public TaskDtoEntityMapper mapper() {
            return new TaskDtoEntityMapperImpl();
        }

        @Bean
        public CreateTaskDtoEntityMapper createMapper() {
            return new CreateTaskDtoEntityMapperImpl();
        }
    }

    @Autowired
    TaskService taskService;

    @MockBean
    TaskRepository taskRepository;
    private static final UUID id = UUID.fromString("439dd7a9-5271-4e17-b071-3c4ce3e319ec");

    @BeforeClass
    public static void setup() {
        Locale.setDefault(new Locale("ru"));
    }

    @Test
    public void successFindByIdTest() {
        TaskEntity taskEntity = new TaskEntity("name", "description", 5L, 6L, "done", 4L, 6L);
        when(taskRepository.findById(id)).thenReturn(of(taskEntity));

        TaskDto taskById = taskService.getTaskById(id);
        assertEquals("name", taskById.getName());
        assertEquals("description", taskById.getDescription());
        assertEquals(TaskStatus.done, taskById.getStatus());
    }

    @Test
    public void emptyResultFindByIdTest() {
        when(taskRepository.findById(id)).thenReturn(empty());
        try {
            taskService.getTaskById(id);
        } catch (Exception e) {
            assertEquals("Задача по ID = 439dd7a9-5271-4e17-b071-3c4ce3e319ec не найдена", e.getMessage());
        }
    }
}