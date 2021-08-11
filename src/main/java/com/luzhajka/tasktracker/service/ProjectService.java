package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.CreateProjectDto;
import com.luzhajka.tasktracker.controller.dto.EditProjectRequestDto;
import com.luzhajka.tasktracker.controller.dto.ProjectDto;

/**
 * Сервис для работы с проектами
 */
public interface ProjectService {

    /**
     * Метод получения проекта по его ID
     *
     * @param projectId - первичный ключ для проекта
     * @return Project DTO
     */
    ProjectDto getProject(Long projectId);

    /**
     * Метод создания проекта
     *
     * @param createProjectDTO - DTO проекта с заполненными полями без ID
     * @return - ID проекта
     */
    Long createProject(CreateProjectDto createProjectDTO);

    /**
     * Метод зименения проекта. Возможно изменение полей: название проекта и заказчик
     *
     * @param projectId - первичный ключ для проекта
     * @param editProjectRequestDto - DTO проекта с полями для измениний
     */
    void editProject(Long projectId, EditProjectRequestDto editProjectRequestDto);

    /**
     * Метод закрытия проекта
     *
     * @param projectId - первичный ключ для проекта
     */
    void completeProject(Long projectId);

}
