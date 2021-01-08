package com.janitha.assignment2.projectservice.service;

//import com.janitha.assignment2.projectservice.model.Project;

import com.janitha.assignment2.commons.model.Project;
import com.janitha.assignment2.projectservice.model.ProjectResponse;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public Project createProject(Project project);
    public ProjectResponse getAllProjects();
    public Project getProjectByCode(int code);
    public Project updateProject(Project project, int projectCode);
    public String deleteProject(int code);
    public String getProjectStatus(int code);
    public List<Project> getAllActiveProjects(String status);

}
