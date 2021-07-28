package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;

import java.util.List;

public interface ReleaseService {
    public ReleaseDto getRelease(Long releaseId);

    public List<ReleaseDto> getReleases(GetReleasesRequestDto getReleasesRequestDto);

    public Long postRelease(CreateReleaseDto createReleaseDto);

    public void editRelease(Long releaseId, EditReleaseRequestDto editReleaseRequestDto);
}
