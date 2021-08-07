package com.luzhajka.tasktracker.repository;

import com.luzhajka.tasktracker.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, Long> {
    List<ReleaseEntity> findAllByProjectId(Long projectId);

}

