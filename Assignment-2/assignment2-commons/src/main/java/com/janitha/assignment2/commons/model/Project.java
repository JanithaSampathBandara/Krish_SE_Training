package com.janitha.assignment2.commons.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    private int code;

    @NotNull
    private String name;

    @NotNull
    private String owner;

    @NotNull
    private String duration;

    @NotNull
    private double budget;

    @NotNull
    private String status; //Initial, Started, Hold, Completed

    @NotNull
    private String resource; //Developer, Manager, Supervisor

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

 //   @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
 //   private List<Task> tasks;

    public int getId() {
        return code;
    }

    public void setId(int id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 //   public List<Task> getTasks() {
 //       return tasks;
 //   }

 //   public void setTasks(List<Task> tasks) {
  //      this.tasks = tasks;
  //  }
}
