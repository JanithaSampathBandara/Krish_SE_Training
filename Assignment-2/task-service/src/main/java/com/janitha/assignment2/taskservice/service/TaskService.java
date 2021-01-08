package com.janitha.assignment2.taskservice.service;

import com.janitha.assignment2.commons.model.Task;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    public Task createTask(Task task);
    public List<Task> getAllTasks();
    public Optional<Task> getTaskById(int id);
    public Task updateTask(Task task, int taskId);
    public String deleteTask(int taskId);
    public String getTaskStatus(int taskId);
    public List<Task> getAllCriticalTasks(String severity);
    public List<Task> getTasksFilterByDate(LocalDate date);

}
