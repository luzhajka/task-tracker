package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import com.luzhajka.tasktracker.entity.ReleaseEntity;
import com.luzhajka.tasktracker.repository.ProjectRepository;
import com.luzhajka.tasktracker.repository.ReleaseRepository;
import com.luzhajka.tasktracker.service.ReleaseService;
import com.luzhajka.tasktracker.utils.ReleaseDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    final ReleaseRepository releaseRepository;
    final ReleaseDtoEntityMapper mapper;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ProjectRepository projectRepository, ReleaseDtoEntityMapper mapper) {
        this.releaseRepository = releaseRepository;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public ReleaseDto getRelease(Long releaseId) {
        Optional<ReleaseEntity> releaseOptional = releaseRepository.findById(releaseId);
        if (releaseOptional.isEmpty()) {
            throw new EntityNotFoundException("Release by ID = " + releaseId + "not found");
        }
        ReleaseEntity releaseEntity = releaseOptional.get();
        return mapper.entityToDto(releaseEntity);
    }

    @Transactional
    @Override
    public List<ReleaseDto> getReleases(GetReleasesRequestDto getReleasesRequestDto) {
        List<ReleaseDto> releases = new ArrayList<>();

        Long projectId = getReleasesRequestDto.getProjectId();
        Optional<List<ReleaseEntity>> releasesOptional = releaseRepository.findAllByProjectId(projectId);
        if (releasesOptional.isEmpty()) {
            throw new EntityNotFoundException("Releases by project ID = " + projectId + " not found");
        }
        List<ReleaseEntity> releaseEntities = releasesOptional.get();
        for (ReleaseEntity entity: releaseEntities) {
            releases.add(mapper.entityToDto(entity));
        }
        return releases;
    }

    @Transactional
    @Override
    public Long postRelease(CreateReleaseDto createReleaseDto) {
        return null;
    }

    @Transactional
    @Override
    public void editRelease(Long releaseId, EditReleaseRequestDto editReleaseRequestDto) {

    }
}
