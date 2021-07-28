package com.luzhajka.tasktracker.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@DynamicUpdate
@Table(name = "releases")
public class ReleaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "start_release")
    private LocalDate startRelease;

    @Column(name = "end_release")
    private LocalDate endRelease;

    @Column(name = "project")
    private Long projectId;

    public ReleaseEntity() {
    }

    public ReleaseEntity(String version, LocalDate startRelease, LocalDate endRelease, Long projectId) {
        this.version = version;
        this.startRelease = startRelease;
        this.endRelease = endRelease;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public LocalDate getStartRelease() {
        return startRelease;
    }

    public LocalDate getEndRelease() {
        return endRelease;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStartRelease(LocalDate startRelease) {
        this.startRelease = startRelease;
    }

    public void setEndRelease(LocalDate endRelease) {
        this.endRelease = endRelease;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}



