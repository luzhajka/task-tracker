package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateReleaseDto;
import com.luzhajka.tasktracker.controller.dto.EditReleaseRequestDto;
import com.luzhajka.tasktracker.controller.dto.GetReleasesRequestDto;
import com.luzhajka.tasktracker.controller.dto.ReleaseDto;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с релизами
 */
public interface ReleaseService {
    ReleaseDto getRelease(Long releaseId);

    /**
     * Метод получения списка релизов проекта по ID проекта
     *
     * @param getReleasesRequestDto - ID проекта
     * @return - списка релизов проекта
     */
    List<ReleaseDto> getReleases(GetReleasesRequestDto getReleasesRequestDto);

    /**
     * Метод получения списка незавершенныз задач релиза
     *
     * @param releaseId - первичный ключ для релиза
     * @return - список незавершенныз задач релиза
     */
    List<UUID> getUnclosedTasksByRelease(Long releaseId);

    /**
     * Метод получения количества незавершенныз задач релиза
     *
     * @param releaseId - первичный ключ для релиза
     * @return - количество незавершенныз задач релиза
     */
    Integer countUnclosedTask(Long releaseId);

    /**
     * Метод создания релиза
     *
     * @param createReleaseDto - DTO релиза с заполненными полями без ID
     * @return - ID релиза (первичный ключ)
     */
    Long createRelease(CreateReleaseDto createReleaseDto);

    /**
     * Метод для изменения релиза с возможностью изменить версию или сроки релиза
     *
     * @param releaseId             - первичный ключ для релиза
     * @param editReleaseRequestDto - DTO проекта с полями для измениний
     */
    void editRelease(Long releaseId, EditReleaseRequestDto editReleaseRequestDto);
}
