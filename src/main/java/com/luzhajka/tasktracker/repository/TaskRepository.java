package com.luzhajka.tasktracker.repository;

import com.luzhajka.tasktracker.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    Optional<List<TaskEntity>> findAllById(UUID taskId);

    Optional<List<TaskEntity>> findAllByRelease(Long releaseId);

    Optional<List<TaskEntity>> findAllByAuthorId(Long authorId);

    Optional<List<TaskEntity>> findAllByExecutorId(Long executorId);

    Optional<List<TaskEntity>> findAllByStatus(String status);

    Optional<List<TaskEntity>> findAllByReleaseIdAndExecutorIdAndStatus(
            Long releaseId,
            Long executorId,
            String status
    );

}

