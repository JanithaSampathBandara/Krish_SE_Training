package com.janitha.trafficoffencemanagement.officerservice.repository;

import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
}
