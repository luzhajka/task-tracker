package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.ReleaseEntity;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.exceptions.EntityNotFoundException;
import com.luzhajka.tasktracker.repository.ReleaseRepository;
import com.luzhajka.tasktracker.repository.TaskRepository;
import com.luzhajka.tasktracker.service.ReleaseService;
import com.luzhajka.tasktracker.utils.CreateReleaseDtoEntityMapper;
import com.luzhajka.tasktracker.utils.ReleaseDtoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

import static com.luzhajka.tasktracker.utils.MessagesUtil.getMessageForLocale;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseServiceImpl.class);
    private final ReleaseRepository releaseRepository;
    private final TaskRepository taskRepository;
    private final ReleaseDtoEntityMapper mapper;
    private final CreateReleaseDtoEntityMapper createMapper;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, TaskRepository taskRepository, ReleaseDtoEntityMapper mapper, CreateReleaseDtoEntityMapper createMapper) {
        this.releaseRepository = releaseRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.createMapper = createMapper;
    }


    @Transactional
    @Override
    public ReleaseDto getRelease(Long releaseId) {
        return releaseRepository
                .findById(releaseId)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        format(getMessageForLocale("release.not.found"), releaseId)
                ));
    }

    @Transactional
    @Override
    public List<ReleaseDto> getReleases(GetReleasesRequestDto getReleasesRequestDto) {
        Long projectId = getReleasesRequestDto.getProjectId();

        return releaseRepository.findAllByProjectId(projectId)
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public List<UUID> getUnclosedTasksByRelease(Long releaseId) {

        List<TaskEntity> unfinishedTaskByRelease = taskRepository
                .findAllByReleaseIdAndStatusIsNot(releaseId, TaskStatus.done.name());

        return unfinishedTaskByRelease.stream()
                .map(TaskEntity::getId)
                .collect(toList());
    }


    @Override
    public Integer countUnclosedTask(Long releaseId) {
        return taskRepository.countAllByReleaseIdAndStatusIsNot(releaseId, TaskStatus.done.name());
    }

    @Transactional
    @Override
    public Long createRelease(CreateReleaseDto createReleaseDto) {
        ReleaseEntity releaseEntity = createMapper.dtoToEntity(createReleaseDto);
        ReleaseEntity releaseEntityResult = releaseRepository.saveAndFlush(releaseEntity);
        return releaseEntityResult.getId();
    }

    @Transactional
    @Override
    public void editRelease(Long releaseId, EditReleaseRequestDto editReleaseRequestDto) {
        ReleaseEntity releaseEntity = releaseRepository.findById(releaseId).orElseThrow(
                () -> new EntityNotFoundException(format(getMessageForLocale("release.not.found"), releaseId))
        );

        releaseEntity.setVersion(StringUtils.hasText(editReleaseRequestDto.getReleaseVersion())
                ? editReleaseRequestDto.getReleaseVersion()
                : releaseEntity.getVersion());

        releaseEntity.setStartRelease(editReleaseRequestDto.getStartDate() != null
                ? editReleaseRequestDto.getStartDate()
                : releaseEntity.getStartRelease());

        releaseEntity.setEndRelease(editReleaseRequestDto.getEndDate() != null
                ? editReleaseRequestDto.getEndDate()
                : releaseEntity.getEndRelease());
    }
}
