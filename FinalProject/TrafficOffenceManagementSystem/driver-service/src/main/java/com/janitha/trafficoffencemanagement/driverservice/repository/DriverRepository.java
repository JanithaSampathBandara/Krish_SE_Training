package com.janitha.trafficoffencemanagement.driverservice.repository;

import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, String> {

    @Query("SELECT driver FROM Driver driver WHERE driver.nic = ?1")
    public Optional<Driver> findByNic(String nic);

}
