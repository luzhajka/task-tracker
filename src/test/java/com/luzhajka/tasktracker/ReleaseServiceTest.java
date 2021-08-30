package com.luzhajka.tasktracker;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.ReleaseEntity;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import com.luzhajka.tasktracker.repository.ReleaseRepository;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.ReleaseService;
import com.luzhajka.tasktracker.service.impl.ReleaseServiceImpl;
import com.luzhajka.tasktracker.utils.CreateReleaseDtoEntityMapper;
import com.luzhajka.tasktracker.utils.CreateReleaseDtoEntityMapperImpl;
import com.luzhajka.tasktracker.utils.ReleaseDtoEntityMapper;
import com.luzhajka.tasktracker.utils.ReleaseDtoEntityMapperImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static com.luzhajka.tasktracker.TestObjectMother.endDate;
import static com.luzhajka.tasktracker.TestObjectMother.getRegularReleaseEntity;
import static com.luzhajka.tasktracker.TestObjectMother.getRegularTaskEntity;
import static com.luzhajka.tasktracker.TestObjectMother.projectId;
import static com.luzhajka.tasktracker.TestObjectMother.releaseId;
import static com.luzhajka.tasktracker.TestObjectMother.releaseVersion;
import static com.luzhajka.tasktracker.TestObjectMother.startDate;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ReleaseServiceTest {

    @TestConfiguration
    static class ReleaseServiceTestContextConfiguration {

        @Bean
        public ReleaseService releaseService(ReleaseRepository releaseRepository, TaskRepository taskRepository,
                                             ReleaseDtoEntityMapper mapper, CreateReleaseDtoEntityMapper createMapper) {
            return new ReleaseServiceImpl(releaseRepository, taskRepository, mapper, createMapper);
        }

        @Bean
        public ReleaseDtoEntityMapper mapper() {
            return new ReleaseDtoEntityMapperImpl();
        }

        @Bean
        public CreateReleaseDtoEntityMapper createMapper() {
            return new CreateReleaseDtoEntityMapperImpl();
        }
    }

    @Autowired
    ReleaseService releaseService;
    @MockBean
    ReleaseRepository releaseRepository;
    @MockBean
    TaskRepository taskRepository;

    @BeforeClass
    public static void setup() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void successGetReleaseByIdTest() {
        when(releaseRepository.findById(releaseId))
                .thenReturn(Optional.of(getRegularReleaseEntity(releaseId)));

        ReleaseDto releaseDto = releaseService.getRelease(releaseId);

        assertEquals(345, releaseDto.getId());
        assertEquals("first version", releaseDto.getVersion());
        assertEquals("2021-06-30", releaseDto.getStartRelease().toString());
        assertEquals("2021-07-07", releaseDto.getEndRelease().toString());
        assertEquals(projectId, releaseDto.getProjectId());
    }

    @Test
    public void emptyGetReleaseByIdTest() {
        when(releaseRepository.findById(releaseId)).thenReturn(empty());
        Exception e = Assertions.assertThrows(EntityNotFoundException.class, () -> releaseService.getRelease(releaseId));
        Assertions.assertEquals(String.format("Release by ID = %d not found", releaseId), e.getMessage());
    }

    @Test
    public void successGetReleasesTest() {
        when(releaseRepository.findAllByProjectId(projectId))
                .thenReturn(List.of(getRegularReleaseEntity(8L), getRegularReleaseEntity(9L)));
        GetReleasesRequestDto getReleasesRequestDto = new GetReleasesRequestDto(projectId);
        List<ReleaseDto> releases = releaseService.getReleases(getReleasesRequestDto);
        releases.forEach(
                (rel) -> Assertions.assertEquals(projectId, rel.getProjectId())
        );
    }

    @Test
    public void emptyGetReleasesTest() {
        when(releaseRepository.findAllByProjectId(projectId)).thenReturn(List.of());
        Assertions.assertTrue(releaseRepository.findAllByProjectId(projectId).isEmpty());
    }

    @Test
    public void successGetUnclosedTasksByReleaseTest() {
        when(taskRepository.findAllByReleaseIdAndStatusIsNot(releaseId, TaskStatus.done.name()))
                .thenReturn(List.of(
                        getRegularTaskEntity(UUID.fromString("439dd7a9-5271-4e17-b071-3c4ce3e319ec")),
                        getRegularTaskEntity(UUID.fromString("539dd7a9-5271-4e17-b071-3c4ce3e319ec"))
                ));

        List<UUID> unclosedTasks = releaseService.getUnclosedTasksByRelease(releaseId);
        Assertions.assertEquals(2, unclosedTasks.size());
        Assertions.assertTrue(unclosedTasks.contains(UUID.fromString("439dd7a9-5271-4e17-b071-3c4ce3e319ec")));
        Assertions.assertTrue(unclosedTasks.contains(UUID.fromString("539dd7a9-5271-4e17-b071-3c4ce3e319ec")));
    }

    @Test
    public void successCountUnclosedTaskTest() {
        when(taskRepository.findAllByReleaseIdAndStatusIsNot(releaseId, TaskStatus.done.name()))
                .thenReturn(List.of(
                        getRegularTaskEntity(UUID.fromString("439dd7a9-5271-4e17-b071-3c4ce3e319ec")),
                        getRegularTaskEntity(UUID.fromString("539dd7a9-5271-4e17-b071-3c4ce3e319ec"))
                ));
        assertTrue(releaseService.countUnclosedTask(releaseId) >= 0);
    }

    @Test
    public void successCreateReleaseTest() {
        when(releaseRepository.saveAndFlush(any(ReleaseEntity.class)))
                .thenReturn(getRegularReleaseEntity(releaseId));

        CreateReleaseDto dto = new CreateReleaseDto(
                releaseVersion,
                startDate,
                endDate,
                projectId
        );
        Long release = releaseService.createRelease(dto);
        assertEquals(releaseId, release);
    }
}
