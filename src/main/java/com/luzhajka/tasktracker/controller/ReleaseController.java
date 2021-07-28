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

@Tag(name = "Управление")
@RestController(value = "${server.api-base-url}")
public class ReleaseController {
    final ReleaseService releaseService;

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
        //заглушка. возвращаем releaseList со всеми релизами
        List<ReleaseDto> releaseList = List.of();
        return releaseList;
    }

    @Operation(summary = "добавить релиз")
    @PostMapping(value = "/release")
    public Long postRelease(@RequestBody CreateReleaseDto createReleaseDTO) {
        //createReleaseDTO в БД
        //БД присвоит releaseID
        Long releaseId = 44L;
        return releaseId;
    }

    @Operation(summary = "изменить версию или сроки релиза")
    @PutMapping(value = "/release/{id}")
    public void editRelease(@PathVariable("id") Long releaseId,
                            @RequestBody EditReleaseRequestDto editReleaseRequestDto) {
    }
}
