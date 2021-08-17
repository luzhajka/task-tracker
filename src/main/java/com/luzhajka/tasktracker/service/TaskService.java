package com.luzhajka.tasktracker.service;

import com.luzhajka.tasktracker.controller.dto.ChangeTaskExecutorDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskReleaseDto;
import com.luzhajka.tasktracker.controller.dto.ChangeTaskStatusDto;
import com.luzhajka.tasktracker.controller.dto.CreateTaskDto;
import com.luzhajka.tasktracker.controller.dto.EditTaskRequestDto;
import com.luzhajka.tasktracker.controller.dto.TaskDto;
import com.luzhajka.tasktracker.controller.dto.TaskFilterRequestDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с задачами
 */
public interface TaskService {

    /**
     * Метод получения задачи по его ID
     *
     * @param taskId - первичный ключ для задачи
     * @return - Task DTO
     */
    TaskDto getTaskById(UUID taskId);

    /**
     * Метод получения задачи по параметрам
     *
     * @param taskFilterRequestDto - DTO c параметрами для фильтра
     * @return - список задач, соответствующих заданным параметрам
     */
    List<TaskDto> getTasksByParameter(TaskFilterRequestDto taskFilterRequestDto);

    /**
     * Метод создания задачи
     *
     * @param createTaskDTO DTO задачи с заполненными полями без ID
     * @return - ID задачи (первичный ключ)
     */
    UUID postTask(CreateTaskDto createTaskDTO);

    /**Метод загрузки задач с помощью CSV файла
     * @param file - CSV файл с задачами
     */
    void uploadTasks(MultipartFile file);

    /**
     * Метод изменения задачи с возможностью изменения названия и описания
     *
     * @param taskId      ID задачи (первичный ключ)
     * @param editTaskDto - DTO задачи с полями для измениний
     */
    void editTask(UUID taskId, EditTaskRequestDto editTaskDto);


    /**
     * Метод изменения исполнителя задачи
     *
     * @param taskId - ID задачи (первичный ключ)
     * @param changeTaskExecutorDto - DTO задачи с измененным полем исполнителя
     */
    void changeExecutor(UUID taskId, ChangeTaskExecutorDto changeTaskExecutorDto);

    /**
     * Метод изменения статуса задачи
     *
     * @param taskId - ID задачи (первичный ключ)
     * @param changeTaskStatusDto - DTO задачи с измененным полем статуса
     */
    void changeStatus(UUID taskId, ChangeTaskStatusDto changeTaskStatusDto);

    /**
     * Метод изменения релиза задачи
     *
     * @param taskId - ID задачи (первичный ключ)
     * @param changeTaskReleaseDto - DTO задачи с измененным полем релиза
     */
    void changeRelease(UUID taskId, ChangeTaskReleaseDto changeTaskReleaseDto);

    /**
     * Метод удаления задачи
     *
     * @param id - ID задачи (первичный ключ)
     */
    void deleteTask(UUID id);
}

