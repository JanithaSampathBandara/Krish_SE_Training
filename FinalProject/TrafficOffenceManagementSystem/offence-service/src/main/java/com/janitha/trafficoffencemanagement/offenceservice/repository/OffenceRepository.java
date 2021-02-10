package com.janitha.trafficoffencemanagement.offenceservice.repository;

import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffenceRepository extends JpaRepository<Offence, Integer> {
}
