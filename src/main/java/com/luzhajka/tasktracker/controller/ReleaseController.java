package com.luzhajka.tasktracker.controller;


import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;
import com.luzhajka.tasktracker.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Управление")
@RestController("${server.api-base-url}")
public class ReleaseController {
    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }


    @Operation(summary = "получить релиз по ID")
    @GetMapping(value = "/release/{id}")
    public ReleaseDto getRelease(@PathVariable("id") Long releaseId) {
        return releaseService.getRelease(releaseId);
    }

    @Operation(summary = "получить список релизов проекта")
    @GetMapping(value = "/releases")
    public List<ReleaseDto> getReleases(@RequestBody GetReleasesRequestDto getReleasesRequestDto) {
        return releaseService.getReleases(getReleasesRequestDto);
    }

    @Operation(summary = "получить список незавершенных задач в релизе")
    @GetMapping(value = "/release/{id}/tasks/unfinished")
    public List<UUID> getUnclosedTasksByRelease(@PathVariable("id") Long releaseId) {
        return releaseService.getUnclosedTasksByRelease(releaseId);
    }

    @Operation(summary = "получить количество незавершенных задач в релизе")
    @GetMapping(value = "/release/{id}/tasks/unfinished/count")
    public Integer countUnclosedTasks(@PathVariable("id") Long releaseId) {
        return releaseService.countUnclosedTask(releaseId);
    }

    @Operation(summary = "добавить релиз")
    @PostMapping(value = "/release")
    public Long createRelease(@RequestBody CreateReleaseDto createReleaseDTO) {
        return releaseService.createRelease(createReleaseDTO);
    }

    @Operation(summary = "изменить версию или сроки релиза")
    @PutMapping(value = "/release/{id}")
    public void editRelease(@PathVariable("id") Long releaseId,
                            @RequestBody EditReleaseRequestDto editReleaseRequestDto) {
        releaseService.editRelease(releaseId, editReleaseRequestDto);
    }




}
