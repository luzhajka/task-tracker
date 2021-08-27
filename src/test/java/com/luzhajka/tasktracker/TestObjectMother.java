package com.luzhajka.tasktracker;

import com.luzhajka.tasktracker.controller.dto.TaskStatus;
import com.luzhajka.tasktracker.entity.ReleaseEntity;
import com.luzhajka.tasktracker.entity.TaskEntity;
import com.luzhajka.tasktracker.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

public class TestObjectMother {

    public static final Long userId = 12L;
    public static final Long releaseId = 345L;
    public static final Long projectId = 555L;
    public static final String releaseVersion = "first version";
    public static final LocalDate startDate = LocalDate.ofYearDay(2021, 181);
    public static final LocalDate endDate = LocalDate.ofYearDay(2021, 188);



    public static ReleaseEntity getRegularReleaseEntity(Long id) {
        ReleaseEntity releaseEntity = new ReleaseEntity(
                releaseVersion,
                startDate,
                endDate,
                projectId
        );
        releaseEntity.setId(id);
        return releaseEntity;
    }

    public static TaskEntity getRegularTaskEntity(UUID id) {
        TaskEntity taskEntity = new TaskEntity(
                "task1",
                "какое-то описание",
                123L,
                321L,
                TaskStatus.inProgress.name(),
                releaseId,
                projectId);
        taskEntity.setId(id);
        return taskEntity;
    }

    public static UserEntity getRegularUserEntity(Long userId){
        UserEntity userEntity = new UserEntity(
                "USER", "user1", "123456", "Ivan", "client"
        );
        userEntity.setId(userId);
        return userEntity;
    }


}
