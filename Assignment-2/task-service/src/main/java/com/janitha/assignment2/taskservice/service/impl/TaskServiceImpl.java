package com.janitha.assignment2.taskservice.service.impl;

import com.janitha.assignment2.commons.model.Project;
import com.janitha.assignment2.commons.model.Task;
import com.janitha.assignment2.taskservice.repository.TaskRepository;
import com.janitha.assignment2.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Task createTask(Task task) {

        System.out.println(task.getProjectId());
            if(validateProject(task.getProjectId()) == 0){
                return taskRepository.save(task);
            }
            else{
                return null;
            }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return task;
        }
        return null;
    }

    @Override
    public Task updateTask(Task task, int taskId) {
        Optional<Task> existingTask = getTaskById(taskId);
        if(existingTask.isPresent()){
            existingTask.get().setName(task.getName());
            existingTask.get().setDescription(task.getDescription());
            existingTask.get().setDuration(task.getDuration());
            return taskRepository.save(existingTask.get());

        }
        return null;
    }

    @Override
    public String deleteTask(int taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return "Successfully Deleted";
        }
        else{
            return "No such project to be deleted";
        }
    }

    @Override
    public String getTaskStatus(int taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            return task.get().getStatus();
        }
        else{
            return null;
        }
    }

    @Override
    public List<Task> getAllCriticalTasks(String severity) {
        Optional<Task> task = taskRepository.getAllCriticalTasks(severity).stream().findAny();
        if(task.isPresent()){
            return taskRepository.getAllCriticalTasks(severity);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Task> getTasksFilterByDate(LocalDate date){
        taskRepository.getTasksFilterByDate(date);
        Optional<Task> task = taskRepository.getTasksFilterByDate(date).stream().findAny();
        if(task.isPresent()){
            return taskRepository.getTasksFilterByDate(date);
        }
        else{
            return null;
        }
    }


    public int validateProject(int projectId){
        try{
            Project  project = restTemplate.getForObject("http://localhost:8082/services/projects/"+projectId,Project.class);

            if(project != null){
                if(project.getStatus().equals("Active")){
                    System.out.println("Project Validated");
                    return 0;
                }
                else{
                    System.out.println("Project Is Not Active");
                    return 1;
                }
            }
            else{
                System.out.println("Project Is Not Exist");
                return -1;
            }
        }
        catch(RestClientException restClientException){
            return -2;
        }catch(Exception exception){
            return -2;
        }
    }
}
