package com.luzhajka.tasktracker.service.impl;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import com.luzhajka.tasktracker.repository.ReleaseRepository;
import com.luzhajka.tasktracker.service.ReleaseService;
import com.luzhajka.tasktracker.utils.ReleaseDtoEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseRepository releaseRepository;
    private final ReleaseDtoEntityMapper mapper;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseDtoEntityMapper mapper) {
        this.releaseRepository = releaseRepository;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public ReleaseDto getRelease(Long releaseId) {
        return releaseRepository
                .findById(releaseId)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new EntityNotFoundException(format("Release by ID = %s not found", releaseId)));

    }

    @Transactional
    @Override
    public List<ReleaseDto> getReleases(GetReleasesRequestDto getReleasesRequestDto) {
        Long projectId = getReleasesRequestDto.getProjectId();

        return releaseRepository.findAllByProjectId(projectId)
                .orElseThrow(
                        () -> new EntityNotFoundException(format("Releases by project ID = %s not found", projectId))
                )
                .stream()
                .map(mapper::entityToDto)
                .collect(toList());
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
