package com.janitha.assignment2.projectservice.service.impl;

import com.janitha.assignment2.commons.model.Project;
import com.janitha.assignment2.projectservice.model.ProjectResponse;
import com.janitha.assignment2.projectservice.repository.ProjectRepository;
import com.janitha.assignment2.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectResponse getAllProjects() {
        ProjectResponse projectResponse = new ProjectResponse();
        Optional<Project> project = projectRepository.findAll().stream().findAny();

        if(project.isPresent()){
            List<Project> projects = projectRepository.findAll();

            projectResponse.setStatusCode(200);
            projectResponse.setDescription("Success");
            System.out.println("aaa");
            projectResponse.setRecordCount(projects.size());
            projectResponse.setProjects(projects);

        }
        else{
            projectResponse.setStatusCode(404);
            projectResponse.setDescription("No Projects Available");
            projectResponse.setRecordCount(0);
        }

        return projectResponse;
    }

    @Override
    public Project getProjectByCode(int code){
        Optional<Project> project = projectRepository.findById(code);

        if(project.isPresent()){

            return project.get();
        }
        else{
            return null;
        }
    }

    @Override
    public Project updateProject(Project project, int projectCode) throws NullPointerException{
        Optional<Project> existingProject = Optional.ofNullable(getProjectByCode(projectCode)); //wrapping using nullable
        if(existingProject.isPresent()){
            existingProject.get().setBudget(project.getBudget());
            existingProject.get().setStatus(project.getStatus());
            existingProject.get().setResource(project.getResource());
            return projectRepository.save(existingProject.get());

        }
        return null;
    }

    @Override
    public String deleteProject(int projectCode){
        Optional<Project> project = projectRepository.findById(projectCode);
        if(project.isPresent()){
            projectRepository.delete(project.get());
            return "Successfully Deleted";
        }
        else{
            return "No such project to be deleted";
        }
    }

    @Override
    public String getProjectStatus(int code) {
        Optional<Project> project = projectRepository.findById(code);
        if(project.isPresent()){
            return project.get().getStatus();
        }
        else{
            return null;
        }
    }

    @Override
    public List<Project> getAllActiveProjects(String status){
        Optional<Project> project = projectRepository.getAllActiveProjects(status).stream().findAny();
        if(project.isPresent()){
            return projectRepository.getAllActiveProjects(status);
        }
        else{
            return null;
        }
    }
}
