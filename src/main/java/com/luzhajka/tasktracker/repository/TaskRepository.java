package com.luzhajka.tasktracker.repository;

import com.luzhajka.tasktracker.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllById(UUID taskId);

    List<TaskEntity> findAllByRelease(Long releaseId);

    List<TaskEntity> findAllByAuthorId(Long authorId);

    List<TaskEntity> findAllByExecutorId(Long executorId);

    List<TaskEntity> findAllByStatus(String status);

    List<TaskEntity> findAllByProjectId(Long projectId);

    List<TaskEntity> findAllByReleaseIdAndExecutorIdAndStatus(
            Long releaseId,
            Long executorId,
            String status
    );

    List<TaskEntity> findAllByProjectIdAndStatusIsNot(
            Long projectId,
            String status
    );

    List<TaskEntity> findAllByReleaseIdAndStatusIsNot(
            Long releaseId,
            String status
    );

    Integer countAllByReleaseIdAndStatusIsNot(
            Long releaseId,
            String status
    );

}

