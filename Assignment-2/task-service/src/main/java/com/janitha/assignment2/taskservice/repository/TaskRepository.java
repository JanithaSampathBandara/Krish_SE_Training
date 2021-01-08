package com.janitha.assignment2.taskservice.repository;

import com.janitha.assignment2.commons.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {


    @Query("SELECT task FROM Task task WHERE task.severity = ?1")
    List<Task> getAllCriticalTasks(String severity);

    @Query("SELECT task FROM Task task WHERE task.endDate <= ?1")
    List<Task> getTasksFilterByDate(LocalDate date);

}
