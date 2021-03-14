package com.janitha.trafficoffencemanagement.driverservice.service.impl;

import com.janitha.trafficoffencemanagement.driverservice.exception.DriverNotFoundException;
import com.janitha.trafficoffencemanagement.driverservice.repository.DriverRepository;
import com.janitha.trafficoffencemanagement.driverservice.service.DriverService;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Override
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver getDriverByLicense(String licenseNo) {
        Optional<Driver> driver = driverRepository.findById(licenseNo);
        if(driver.isPresent()){
            return driver.get();
        }
        else{
            throw new DriverNotFoundException("No driver found for this license number : " + licenseNo);
        }
        // return driverRepository.findById(licenseNo);
    }

    @Override
    public Driver getDriverByNic(String nic) {
        Optional<Driver> driver = driverRepository.findByNic(nic);
        if(driver.isPresent()){
            return driver.get();
        }
        else{
            throw new DriverNotFoundException("No driver found for this NIC number : " + nic);
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        //   Optional<Driver> driver =  driverRepository.findAll().stream().findAny();
        List<Driver> drivers =  driverRepository.findAll();
        if(!drivers.isEmpty()){
            return drivers;
        }
        else{
            throw new DriverNotFoundException("No drivers found");
        }
    }

/*
    @Override
    public List<Driver> getAllDrivers() {
     //   Optional<Driver> driver =  driverRepository.findAll().stream().findAny();
        List<Driver> drivers =  driverRepository.findAll();
        if(!drivers.isEmpty()){
            return drivers;
        }
        else{
            throw new DriverNotFoundException("No drivers found");
        }
    }
*/
    @Override
    public Driver updateDriver(Driver driver, String licenseNo) {
        Optional<Driver> existingDriver = driverRepository.findById(licenseNo);
        if(existingDriver.isPresent()){
            existingDriver.get().setName(driver.getName());
            existingDriver.get().setAddress(driver.getAddress());
            existingDriver.get().setNic(driver.getNic());
            existingDriver.get().setPhone(driver.getPhone());
            existingDriver.get().setEmail(driver.getEmail());
            existingDriver.get().setGender(driver.getGender());
            existingDriver.get().setDob(driver.getDob().toString()); // previous existingDriver.get().setDob(driver.getDob());
            existingDriver.get().setPassword(driver.getPassword());
           return driverRepository.save(existingDriver.get());
        }
        else{
            throw new DriverNotFoundException("No driver is found from this license no : " + licenseNo + " to update ");
        }
    }

    public void deleteDriver(String licenseNo){
        Optional<Driver> existingDriver = driverRepository.findById(licenseNo);
        if(existingDriver.isPresent()){
            driverRepository.delete(existingDriver.get());
        }
        else{
            throw new DriverNotFoundException("No driver for this id : " + licenseNo + " to delete");
        }
    }

    /*
    @Override
    public Optional<Driver> createDriver(Driver driver) {
        Optional<Driver> savedDriver = Optional.of(driverRepository.save(driver));
        if(savedDriver.isPresent()){
            return savedDriver;
        }
        else{
           return Optional.empty();
        }
    }

     */
}
