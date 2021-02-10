package com.janitha.trafficoffencemanagement.driverservice.service;

import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.NestedServletException;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public Driver createDriver(Driver driver);
    public Driver getDriverByLicense(String licenseNo);
    public Driver getDriverByNic(String nic);
    public List<Driver> getAllDrivers();
    public Driver updateDriver(Driver driver, String licenseNo);
    public void deleteDriver(String licenseNo);

}
