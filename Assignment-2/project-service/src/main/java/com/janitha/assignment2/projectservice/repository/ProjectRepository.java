package com.janitha.assignment2.projectservice.repository;

//import com.janitha.assignment2.projectservice.model.Project;
import com.janitha.assignment2.commons.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    //also refer JPQL Query
   // @Query("SELECT p FROM Project p WHERE p.status = :status")
    @Query("SELECT p FROM Project p WHERE p.status = ?1")
    List<Project> getAllActiveProjects(String status);

    //Custom methd without implementation to get projects by name -- Method name should start with findBy then attribute which exist in the class
    List<Project> findByName(String name);

}
