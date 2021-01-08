package com.janitha.assignment2.projectservice.controller;

import com.janitha.assignment2.commons.model.Project;
import com.janitha.assignment2.projectservice.model.ProjectResponse;
import com.janitha.assignment2.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        System.out.println("createProject");

            return ResponseEntity.ok().body(projectService.createProject(project));


    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ResponseEntity getAllProjects(){
        ProjectResponse projectResponse = new ProjectResponse();

        try{
            projectResponse = projectService.getAllProjects();

        }catch(Exception exception){

            System.out.println(exception.getMessage());
            System.out.println(exception.getLocalizedMessage());

            projectResponse.setStatusCode(-1);
            projectResponse.setDescription(exception.getMessage());
        }

        return ResponseEntity.ok().body(projectResponse);

    }

    @RequestMapping(value = "/projects/{projectCode}", method = RequestMethod.GET)
    public ResponseEntity getProjectByCode(@PathVariable int projectCode){
        System.out.println("getProjectByCode");
        try{

            if(projectService.getProjectByCode(projectCode) != null){
                Project project = projectService.getProjectByCode(projectCode);
                return ResponseEntity.ok().body(project);
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).body("No Project Found For This Project Code : "+projectCode);
            }

        }catch(NullPointerException nullPointerException){
            return ResponseEntity.status(HttpStatus.OK).body(nullPointerException.getMessage());
        }
        catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }
    }

    @RequestMapping(value = "/projects/{projectCode}", method = RequestMethod.PUT)
    public ResponseEntity updateProject(Project project, @PathVariable int projectCode){
        System.out.println("updateProject");
        try{
            return ResponseEntity.ok().body(projectService.updateProject(project, projectCode));

        }catch(NullPointerException nullPointerException){
            return ResponseEntity.status(HttpStatus.OK).body(nullPointerException.getMessage());
        }
        catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }

    }


    @RequestMapping(value = "/projects/{projectCode}", method = RequestMethod.DELETE)
    public String deleteProject(@PathVariable int projectCode){
        System.out.println("deleteProject");
        return projectService.deleteProject(projectCode);
    }

    @RequestMapping(value = "/projects/{projectCode}/status", method = RequestMethod.GET)
    public ResponseEntity<String> getProjectStatus(@PathVariable int projectCode){
        System.out.println("getProjectStatus");
        String status ="";
        try{
             status = projectService.getProjectStatus(projectCode);
        }catch(NullPointerException nullPointerException){
             return new ResponseEntity<String>("No Project Found For This Code " +projectCode + "", HttpHeaders.EMPTY, HttpStatus.OK);
        }
        catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/", method = RequestMethod.GET)
    public ResponseEntity getAllActiveProjects(@RequestParam("status") String status){
        System.out.println("getAllActiveProjects");
        List<Project> activeProjects = null;
        try{
            activeProjects = projectService.getAllActiveProjects(status);
        }catch(NullPointerException nullPointerException){
            return ResponseEntity.status(HttpStatus.OK).body("No Active Projects");
        }
        catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }

        return ResponseEntity.ok().body(activeProjects);
    }
}
