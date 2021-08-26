package com.luzhajka.tasktracker;

import com.luzhajka.tasktracker.client.ClientAccountServiceClient;
import com.luzhajka.tasktracker.client.dto.PaymentDto;
import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;
import com.luzhajka.tasktracker.entity.ProjectEntity;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import com.luzhajka.tasktracker.exceptions.InvalidProjectStateException;
import com.luzhajka.tasktracker.exceptions.PaymentNotFoundException;
import com.luzhajka.tasktracker.repository.ProjectRepository;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.ProjectService;
import com.luzhajka.tasktracker.service.impl.ProjectServiceImpl;
import com.luzhajka.tasktracker.utils.CreateProjectDtoEntityMapper;
import com.luzhajka.tasktracker.utils.CreateProjectDtoEntityMapperImpl;
import com.luzhajka.tasktracker.utils.ProjectDtoEntityMapper;
import com.luzhajka.tasktracker.utils.ProjectDtoEntityMapperImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.luzhajka.tasktracker.controller.dto.ProjectStatus.DONE;
import static com.luzhajka.tasktracker.controller.dto.ProjectStatus.IN_PROGRESS;
import static com.luzhajka.tasktracker.controller.dto.ProjectStatus.WAITING;
import static com.luzhajka.tasktracker.controller.dto.TaskStatus.done;
import static com.luzhajka.tasktracker.controller.dto.TaskStatus.inProgress;
import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @TestConfiguration
    static class ProjectServiceTestContextConfiguration {
        @Bean
        public ProjectService projectService(
                ProjectRepository projectRepository,
                ProjectDtoEntityMapper mapper,
                CreateProjectDtoEntityMapper createMapper,
                TaskRepository taskRepository,
                ClientAccountServiceClient client
        ) {
            return new ProjectServiceImpl(projectRepository, mapper, createMapper, taskRepository, client);
        }

        @Bean
        public ProjectDtoEntityMapper mapper() {
            return new ProjectDtoEntityMapperImpl();
        }

        @Bean
        public CreateProjectDtoEntityMapper createMapper() {
            return new CreateProjectDtoEntityMapperImpl();
        }
    }

    @Autowired
    ProjectService projectService;
    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    ClientAccountServiceClient client;

    private static final Long projectId = 3434L;
    private static final Long clientId = 8L;
    private static final String projectName = "SuperProject";

    @BeforeClass
    public static void setup() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void successGetProjectTest() {
        ProjectEntity entity = new ProjectEntity(projectName, IN_PROGRESS.name(), clientId);
        entity.setId(projectId);
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(entity));

        ProjectDto projectDto = projectService.getProject(projectId);

        Assertions.assertEquals(IN_PROGRESS, projectDto.getStatus());
        Assertions.assertEquals(projectName, projectDto.getProjectName());
        Assertions.assertEquals(3434, projectDto.getId());
        Assertions.assertEquals("8", projectDto.getClientId());
    }

    @Test
    public void emptyResultGetProjectTest() {
        Mockito.when(projectRepository.findById(projectId)).thenReturn(empty());

        try {
            projectService.getProject(projectId);
        } catch (Exception e) {
            Assertions.assertEquals("Project by ID = 3434 not found", e.getMessage());
        }
    }

    @Test
    public void throwExceptionWhenProjectHasOpenedTaskInCompleteProjectTest() {
        TaskEntity taskEntity = new TaskEntity(
                "megaTask",
                "so difficult",
                5L,
                6L,
                inProgress.name(),
                4L,
                projectId
        );
        Mockito.when(taskRepository.findAllByProjectIdAndStatusIsNot(projectId, done.name()))
                .thenReturn(List.of(taskEntity));

        Exception e = Assertions.assertThrows(InvalidProjectStateException.class, () -> projectService.completeProject(projectId));
        Assertions.assertEquals("There are unfinished tasks left on the project", e.getMessage());

    }

    @Test
    public void successCompleteProjectTest() {

        Mockito.when(taskRepository.findAllByProjectIdAndStatusIsNot(projectId, done.name()))
                .thenReturn(List.of());
        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(new ProjectEntity(projectName, IN_PROGRESS.name(), clientId)));
        ProjectEntity resultProjectEntity = new ProjectEntity(projectName, DONE.name(), clientId);
        Mockito.when(projectRepository.saveAndFlush(resultProjectEntity))
                .thenReturn(resultProjectEntity);

        projectService.completeProject(projectId);

        Mockito.verify(projectRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void throwProjectNotFoundInCompleteProjectTest() {

        Mockito.when(taskRepository.findAllByProjectIdAndStatusIsNot(projectId, done.name()))
                .thenReturn(List.of());
        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(EntityNotFoundException.class, () -> projectService.completeProject(projectId));
        Assertions.assertEquals("Project by ID = 3434 not found", e.getMessage());
        Mockito.verify(projectRepository, times(0)).saveAndFlush(any());
        Mockito.verify(projectRepository, times(1)).findById(any());
    }

    @Test
    public void successStartProjectTest() {
        Mockito.when(client.getProjectPayment(projectId))
                .thenReturn(List.of(new PaymentDto()));
        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(new ProjectEntity(projectName, WAITING.name(), clientId)));

        ProjectEntity resultProjectEntity = new ProjectEntity(projectName, IN_PROGRESS.name(), clientId);
        Mockito.when(projectRepository.saveAndFlush(resultProjectEntity))
                .thenReturn(resultProjectEntity);

        projectService.startProject(projectId);

        Mockito.verify(projectRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void throwNoMoneyExceptionInStartProjectTest() {
        Mockito.when(client.getProjectPayment(projectId))
                .thenReturn(List.of());


        Exception e = Assertions.assertThrows(PaymentNotFoundException.class, () -> projectService.startProject(projectId));
        Assertions.assertEquals("no payments were found for the 3434 project", e.getMessage());
        Mockito.verify(projectRepository, times(0)).saveAndFlush(any());
        Mockito.verify(projectRepository, times(0)).findById(any());
    }

    @Test
    public void throwProjectNotFoundInStartProjectTest() {
        Mockito.when(client.getProjectPayment(projectId))
                .thenReturn(List.of(new PaymentDto()));
        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(EntityNotFoundException.class, () -> projectService.startProject(projectId));
        Assertions.assertEquals("Project by ID = 3434 not found", e.getMessage());
        Mockito.verify(projectRepository, times(0)).saveAndFlush(any());
        Mockito.verify(projectRepository, times(1)).findById(any());
    }


    @Test
    public void successCreateProjectTest() {
        ProjectEntity responseEntity = new ProjectEntity(projectName, WAITING.name(), clientId);
        responseEntity.setId(projectId);
        Mockito.when(projectRepository.saveAndFlush(any())).thenReturn(responseEntity);

        Long createdProjectId = projectService.createProject(new CreateProjectDto(projectName, clientId));

        Assertions.assertEquals(projectId, createdProjectId);
    }
}
