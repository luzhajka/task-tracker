package com.luzhajka.tasktracker;

import com.luzhajka.tasktracker.controller.dto.ChangeTaskExecutorDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskReleaseDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskStatusDto;
import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.InvalidFilterException;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.TaskService;
import com.luzhajka.tasktracker.service.impl.TaskServiceImpl;
import com.luzhajka.tasktracker.utils.CreateTaskDtoEntityMapper;
import com.luzhajka.tasktracker.utils.CreateTaskDtoEntityMapperImpl;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapper;
import com.luzhajka.tasktracker.utils.TaskDtoEntityMapperImpl;
import liquibase.util.csv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static com.luzhajka.tasktracker.TestObjectMother.getRegularTaskEntity;
import static com.luzhajka.tasktracker.TestObjectMother.projectId;
import static com.luzhajka.tasktracker.TestObjectMother.releaseId;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    public void successGetByIdTest() {
        when(taskRepository.findById(id)).thenReturn(of(getRegularTaskEntity(id)));

        TaskDto taskById = taskService.getTaskById(id);
        assertEquals("task1", taskById.getName());
        assertEquals("какое-то описание", taskById.getDescription());
        assertEquals(TaskStatus.inProgress, taskById.getStatus());
    }

    @Test
    public void emptyResultGetByIdTest() {
        when(taskRepository.findById(id)).thenReturn(empty());
        try {
            taskService.getTaskById(id);
        } catch (Exception e) {
            assertEquals("Задача по ID = 439dd7a9-5271-4e17-b071-3c4ce3e319ec не найдена", e.getMessage());
        }
    }

    @Test
    public void getTasksByParametersReleaseExecutorStatusTest() {
        TaskFilterRequestDto taskFilterRequestDto = new TaskFilterRequestDto(
                releaseId, null, 20L, "done"
        );
        when(taskRepository.findAllByReleaseIdAndExecutorIdAndStatus(
                taskFilterRequestDto.getReleaseId(),
                taskFilterRequestDto.getExecutorId(),
                taskFilterRequestDto.getStatus()))
                .thenReturn(List.of(getRegularTaskEntity(UUID.randomUUID()), getRegularTaskEntity(UUID.randomUUID())));
        List<TaskDto> tasksByParameters = taskService.getTasksByParameter(taskFilterRequestDto);
        assertEquals(2, tasksByParameters.size());
    }

    @Test
    public void getTasksByStatusTest() {
        TaskFilterRequestDto taskFilterRequestDto = new TaskFilterRequestDto(
                null, null, null, "done"
        );
        when(taskRepository.findAllByStatus(taskFilterRequestDto.getStatus()))
                .thenReturn(List.of(getRegularTaskEntity(UUID.randomUUID()), getRegularTaskEntity(UUID.randomUUID())));
        List<TaskDto> tasksByParameters = taskService.getTasksByParameter(taskFilterRequestDto);
        assertEquals(2, tasksByParameters.size());
    }

    @Test
    public void getTasksByAuthorTest() {
        TaskFilterRequestDto taskFilterRequestDto = new TaskFilterRequestDto(
                null, 10L, null, null
        );
        when(taskRepository.findAllByAuthorId(
                taskFilterRequestDto.getAuthorId()))
                .thenReturn(List.of(getRegularTaskEntity(UUID.randomUUID()), getRegularTaskEntity(UUID.randomUUID())));
        List<TaskDto> tasksByParameters = taskService.getTasksByParameter(taskFilterRequestDto);
        assertEquals(2, tasksByParameters.size());
    }

    @Test
    public void getTasksByReleaseTest() {
        TaskFilterRequestDto taskFilterRequestDto = new TaskFilterRequestDto(
                releaseId, null, null, null
        );
        when(taskRepository.findAllByReleaseId(
                taskFilterRequestDto.getReleaseId()))
                .thenReturn(List.of(getRegularTaskEntity(UUID.randomUUID()), getRegularTaskEntity(UUID.randomUUID())));
        List<TaskDto> tasksByParameters = taskService.getTasksByParameter(taskFilterRequestDto);
        assertEquals(2, tasksByParameters.size());
    }

    @Test
    public void getTasksByExecutorTest() {
        TaskFilterRequestDto taskFilterRequestDto = new TaskFilterRequestDto(
                null, null, 20L, null
        );
        when(taskRepository.findAllByExecutorId(
                taskFilterRequestDto.getExecutorId()))
                .thenReturn(List.of(getRegularTaskEntity(UUID.randomUUID()), getRegularTaskEntity(UUID.randomUUID())));
        List<TaskDto> tasksByParameters = taskService.getTasksByParameter(taskFilterRequestDto);
        assertEquals(2, tasksByParameters.size());
    }

    @Test
    public void getTasksTest() {
        TaskFilterRequestDto taskFilterRequestDto = new TaskFilterRequestDto(
                null, null, null, null
        );
        when(taskRepository.findAll())
                .thenReturn(List.of(getRegularTaskEntity(UUID.randomUUID()), getRegularTaskEntity(UUID.randomUUID())));
        List<TaskDto> tasksByParameters = taskService.getTasksByParameter(taskFilterRequestDto);
        assertEquals(2, tasksByParameters.size());
    }

    @Test
    public void throwInvalidFilterExceptionInGetTasksTest() {
        Exception e = Assertions.assertThrows(InvalidFilterException.class, () -> taskService.getTasksByParameter(
                new TaskFilterRequestDto(releaseId, 10L, null, null)
        ));
        Assertions.assertEquals("Получен не верный набор параметров для поиска", e.getMessage());
    }

    @DirtiesContext
    @Test
    public void postTaskTest() {
        CreateTaskDto createTaskDto = new CreateTaskDto
                ("task1", "какое-то описание", 123L,  321L,
                        TaskStatus.valueOf("inProgress"), releaseId, projectId);
        TaskEntity regularTaskEntity = getRegularTaskEntity(id);
        when(taskRepository.saveAndFlush(any())).thenReturn(regularTaskEntity);
        UUID taskId = taskService.postTask(createTaskDto);

        assertEquals(id, taskId);
        verify(taskRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void editTaskTest() {
        EditTaskRequestDto editTaskDto = new EditTaskRequestDto("task2", "new description");
        TaskEntity regularTaskEntity = getRegularTaskEntity(id);
        when(taskRepository.findById(id)).thenReturn(of(regularTaskEntity));

        taskService.editTask(id, editTaskDto);
        assertEquals("task2", regularTaskEntity.getName());
        assertEquals("new description", regularTaskEntity.getDescription());
        Mockito.verify(taskRepository, times(1)).saveAndFlush(regularTaskEntity);
    }


    @Test
    public void changeExecutorTest() {
        ChangeTaskExecutorDto changeTaskExecutorDto = new ChangeTaskExecutorDto(222L);
        TaskEntity regularTaskEntity = getRegularTaskEntity(id);
        when(taskRepository.findById(id)).thenReturn(Optional.of(regularTaskEntity));

        taskService.changeExecutor(id, changeTaskExecutorDto);
        assertEquals(222L, regularTaskEntity.getExecutorId());
        Mockito.verify(taskRepository, times(1)).saveAndFlush(regularTaskEntity);
    }


    @Test
    public void changeStatusTest() {
        ChangeTaskStatusDto changeTaskStatusDto = new ChangeTaskStatusDto(TaskStatus.valueOf("done"));
        TaskEntity regularTaskEntity = getRegularTaskEntity(id);
        when(taskRepository.findById(id)).thenReturn(Optional.of(regularTaskEntity));

        taskService.changeStatus(id, changeTaskStatusDto);
        assertEquals("done", regularTaskEntity.getStatus());
        Mockito.verify(taskRepository, times(1)).saveAndFlush(regularTaskEntity);
    }

    @Test
    public void changeReleaseTest() {
        ChangeTaskReleaseDto changeTaskReleaseDto = new ChangeTaskReleaseDto(44L);
        TaskEntity regularTaskEntity = getRegularTaskEntity(id);
        when(taskRepository.findById(id)).thenReturn(Optional.of(regularTaskEntity));
        taskService.changeRelease(id, changeTaskReleaseDto);

        assertEquals(44L, regularTaskEntity.getReleaseId());
        Mockito.verify(taskRepository, times(1)).saveAndFlush(regularTaskEntity);
    }

    @Test
    public void deleteTaskTest() {
        TaskEntity regularTaskEntity = getRegularTaskEntity(id);
        when(taskRepository.findById(id)).thenReturn(Optional.of(regularTaskEntity));
        taskService.deleteTask(id);
        Mockito.verify(taskRepository, times(1)).delete(regularTaskEntity);
    }


    @Test
    public void uploadTasksTest() throws IOException {
        InputStream csvStream = getClass().getResource("/CSVtesttask.csv").openStream();
        MultipartFile multipartFile = new MockMultipartFile("file",
                "CSVtesttask.csv", "text/plain", IOUtils.toByteArray(csvStream));

        TaskEntity taskEntity = getRegularTaskEntity(id);

        when(taskRepository.saveAndFlush(any())).thenReturn(taskEntity);
        taskService.uploadTasks(multipartFile);
        Mockito.verify(taskRepository, times(2)).saveAndFlush(any());
    }
}
