package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface ReleaseService {
    public ReleaseDto getRelease(Long releaseId);

    public List<ReleaseDto> getReleases(GetReleasesRequestDto getReleasesRequestDto);

    public List<UUID> getUnclosedTasksByRelease(Long releaseId);

    public Integer countUnclosedTask(Long releaseId);

    public Long createRelease(CreateReleaseDto createReleaseDto);

    public void editRelease(Long releaseId, EditReleaseRequestDto editReleaseRequestDto);
}
