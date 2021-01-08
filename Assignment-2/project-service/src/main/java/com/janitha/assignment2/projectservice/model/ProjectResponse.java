package com.janitha.assignment2.projectservice.model;

import com.janitha.assignment2.commons.model.Project;

import java.util.List;

public class ProjectResponse {

    private int statusCode;
    private String description;
    private int recordCount;
    private List<Project> projects;
    private Project project;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<com.janitha.assignment2.commons.model.Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setProjectss(Project project) {
        this.projects.add(project);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
