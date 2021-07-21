package com.luzhajka.tasktracker.controller;


import com.luzhajka.tasktracker.controller.dto.CreateReleaseDTO;
import com.luzhajka.tasktracker.controller.dto.ProjectDTO;
import com.luzhajka.tasktracker.controller.dto.ProjectStatus;
import com.luzhajka.tasktracker.controller.dto.ReleaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Управление релизами")
@RequestMapping("/api/releaseManager")
@RestController
public class ReleaseController {


    @Operation(summary = "получить релиз по ID")
    @GetMapping(value = "/release/{releaseID}")
    public ReleaseDTO getRelease(@RequestHeader("requester") String requester,
                                 @PathVariable("releaseID") String releaseID) {
        //заглушка. Вернуть релиз по releaseID
        return new ReleaseDTO(122L, "2.2", LocalDate.now(), LocalDate.now().plusDays(7L), new ProjectDTO(12L, "", "", ProjectStatus.DONE));
    }

    @Operation(summary = "получить список релизов проекта")
    @GetMapping(value = "/releases/{projectID}")
    public List<ReleaseDTO> getReleases(@RequestHeader("requester") String requester,
                                        @PathVariable("projectID") Long projectID) {
        //заглушка. возвращаем releaseList со всеми релизами
        List<ReleaseDTO> releaseList = List.of();
        return releaseList;
    }

    @Operation(summary = "добавить релиз")
    @PostMapping(value = "/release")
    public Long postRelease(@RequestHeader("requester") String requester,
                            @RequestBody CreateReleaseDTO createReleaseDTO) {
        //createReleaseDTO в БД
        //БД присвоит releaseID
        Long releaseID = 44L;
        return releaseID;
    }

    @Operation(summary = "изменить версию или сроки релиза")
    @PutMapping(value = "/release/{releaseID}")
    public void editRelease(@RequestHeader("requester") String requester,
                            @PathVariable("releaseID") String releaseID,
                            @RequestParam(name = "releaseVersion") String releaseVersion,
                            @RequestParam(name = "startDate") String startDate,
                            @RequestParam(name = "endDate") String endDate) {
    }

}
