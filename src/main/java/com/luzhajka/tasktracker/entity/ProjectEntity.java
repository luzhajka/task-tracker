package com.luzhajka.tasktracker.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@DynamicUpdate
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "status")
    private String status;

    @Column(name = "client")
    private Long clientId;


    public ProjectEntity() {
    }

    public ProjectEntity(String projectName, String status, Long clientId) {
        this.projectName = projectName;
        this.status = status;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    public Long getClientId() {
        return clientId;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
