package com.janitha.assignment2.taskservice.controller;

import com.janitha.assignment2.commons.model.Task;
import com.janitha.assignment2.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "services/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task){
        System.out.println("createTask");
        Task newTask = taskService.createTask(task);
        if(newTask != null){
            return ResponseEntity.ok().body(newTask);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body("Task Creation Failed");
        }

    }

    @GetMapping
    public List<Task> getAllTasks(){
        System.out.println("getAllTasks");
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity getTaskById(@PathVariable int taskId){
        System.out.println("getTaskById");

        try{
            Task task = taskService.getTaskById(taskId).get();

                return ResponseEntity.ok().body(task);

            } catch(NullPointerException nullPointerException){
                return ResponseEntity.status(HttpStatus.OK).body("No Task For This Id : "+taskId+"");
        }

    }

    @PutMapping(value = "/{taskId}")
    public Task updateTask(@RequestBody Task task, @PathVariable int taskId){
        System.out.println("updateTask");
        return taskService.updateTask(task, taskId);

    }

    @DeleteMapping(value = "/{taskId}")
    public String deleteTask(@PathVariable int taskId){
        System.out.println("deleteTask");
        return taskService.deleteTask(taskId);

    }

    @GetMapping("/{taskId}/status")
    public ResponseEntity<String> getTaskStatus(@PathVariable int taskId){
        try{
            System.out.println("getTaskStatus");
            String status = taskService.getTaskStatus(taskId);
                if(status != null){
                    return ResponseEntity.ok(status);
                }
                else{
                    return ResponseEntity.status(HttpStatus.OK).body("No Task For This Id : "+taskId+"");

                }

        }catch(NullPointerException nullPointerException){
            return ResponseEntity.status(HttpStatus.OK).body(nullPointerException.getMessage());
        }
        catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }

    }

    @GetMapping(value = "/")
    public List<Task> getAllCriticalTasks(@RequestParam("severity") String severity){
        System.out.println("getAllCriticalTasks");
        return taskService.getAllCriticalTasks(severity);

    }

    @GetMapping(value = "/hello")
    public ResponseEntity getTasksFilterByDate(@RequestParam("endDate") String date) {

        try{
            String retrievedDate = date;
            LocalDate datee = LocalDate.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date formattedDate = formatter.parse(retrievedDate);// have to handle null exception here
            formatter.format(formattedDate);
            System.out.println(formattedDate);
            return ResponseEntity.ok().body(taskService.getTasksFilterByDate(datee));

        }catch(ParseException parseException){
            return ResponseEntity.status(HttpStatus.OK).body(parseException.getMessage());
        }
        catch(DateTimeParseException dateTimeParseException){
            return ResponseEntity.status(HttpStatus.OK).body("Date Format Should Be : yyyy-mm-dd");
        }
        catch(NullPointerException nullPointerException){
            return ResponseEntity.status(HttpStatus.OK).body(nullPointerException.getMessage());
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
        }


    }

}
