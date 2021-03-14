package com.janitha.trafficoffencemanagement.driverservice.controller;

import com.janitha.trafficoffencemanagement.driverservice.exception.DriverNotFoundException;
import com.janitha.trafficoffencemanagement.driverservice.exception.ErrorDetails;
import com.janitha.trafficoffencemanagement.driverservice.service.DriverService;
import com.janitha.trafficoffencemanagement.driverservice.service.impl.DriverServiceImpl;
import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.NestedServletException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/services/drivers")
public class DriverController {

    Logger logger = LoggerFactory.getLogger(DriverController.class);

    Response driverResponse = new Response();

    @Autowired
    DriverService driverService;

  /*  @GetMapping
    public String greeting(){
        return "Janzz";
    }
*/
    @PostMapping
    @PreAuthorize("hasAuthority('create_driver')")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver){
         return new ResponseEntity<Driver>(driverService.createDriver(driver),HttpStatus.CREATED);
    }

    @GetMapping("/{licenseNo}")
    @PreAuthorize("hasAuthority('read_driver')")
    public ResponseEntity<Response> getDriverByLicense(@PathVariable String licenseNo){
        System.out.println("inside getDriverByLicense");
        try{
            Driver driver = driverService.getDriverByLicense(licenseNo);
            driverResponse.setStatusCode(0);
            driverResponse.setMessage("Driver Found");
            driverResponse.setData(driver);
            System.out.println("driver respone from driver controller"+ driverResponse);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);

        }catch(DriverNotFoundException driverNotFoundException){

            logger.error(driverNotFoundException.getMessage());

            driverResponse.setStatusCode(-99);
            driverResponse.setMessage(driverNotFoundException.getMessage());
            driverResponse.setData(null);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);

           // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found");
     /*       ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setResponseCode(404);
            errorDetails.setTimeStamp(new Date());
            errorDetails.setMessage(driverNotFoundException.getMessage());
            errorDetails.setDetails("Use existing driver id");

            System.out.println(driverNotFoundException.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND); */
        }


      //  Driver driver = driverService.getDriverByLicense(licenseNo).orElseThrow(()->new  ResourceNotFoundException("Driver not found for this id : " + licenseNo));
   /*
   Optional<Driver> driver = driverService.getDriverByLicense(licenseNo);
        if(driver.isPresent()){
            return ResponseEntity.ok().body(driver.get());
        }

        return new
        return ResponseEntity.ok().body(driver);

    */

    }
    @GetMapping("nic/{nic}")
    @PreAuthorize("hasAuthority('read_driver')")
    public ResponseEntity<Response> getDriverByNic(@PathVariable("nic") String nic){

        try{
            Driver driver = driverService.getDriverByNic(nic);
            driverResponse.setStatusCode(0);
            driverResponse.setMessage("Driver Found");
            driverResponse.setData(driver);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);

        }catch(DriverNotFoundException driverNotFoundException){

            logger.error(driverNotFoundException.getMessage());

            driverResponse.setStatusCode(-99);
            driverResponse.setMessage(driverNotFoundException.getMessage());
            driverResponse.setData(null);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);
        }

    }

    @GetMapping
    @PreAuthorize("hasAuthority('read_driver')")
    public ResponseEntity<Response> getAllDrivers(){
        try{
            List<Driver> drivers = driverService.getAllDrivers();

            driverResponse.setStatusCode(0);
            driverResponse.setMessage("Drivers Found");
            driverResponse.setData(drivers);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);

        }catch(DriverNotFoundException driverNotFoundException){

            logger.error(driverNotFoundException.getMessage());

            driverResponse.setStatusCode(-99);
            driverResponse.setMessage(driverNotFoundException.getMessage());
            driverResponse.setData(null);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);
        }
    }

/*
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers(){
        try{
            return new ResponseEntity<>(driverService.getAllDrivers(),HttpStatus.OK);
        }catch(DriverNotFoundException driverNotFoundException){
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setResponseCode(404);
            errorDetails.setTimeStamp(new Date());
            errorDetails.setMessage(driverNotFoundException.getMessage());
            errorDetails.setDetails("No drivers present in the database");

            System.out.println(driverNotFoundException.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
        }
    }
*/
    @PutMapping("{licenseNo}")
    @PreAuthorize("hasAuthority('update_driver')")
    public ResponseEntity<Response> updateDriver(@RequestBody Driver driver, @PathVariable String licenseNo){
        try{

            Driver updatedDriver = driverService.updateDriver(driver, licenseNo);
            driverResponse.setStatusCode(0);
            driverResponse.setMessage("Driver Updated");
            driverResponse.setData(updatedDriver);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);

        }catch(DriverNotFoundException driverNotFoundException){

            logger.error(driverNotFoundException.getMessage());

            driverResponse.setStatusCode(-99);
            driverResponse.setMessage(driverNotFoundException.getMessage());
            driverResponse.setData(null);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);

        }
    }

    @DeleteMapping("{licenseNo}")
    @PreAuthorize("hasAuthority('delete_driver')")
    public ResponseEntity deleteDriver(@PathVariable String licenseNo){
        try{
            driverService.deleteDriver(licenseNo);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch(DriverNotFoundException driverNotFoundException){

            logger.error(driverNotFoundException.getMessage());

            driverResponse.setStatusCode(-99);
            driverResponse.setMessage(driverNotFoundException.getMessage());
            driverResponse.setData(null);
            return new ResponseEntity<Response>(driverResponse, HttpStatus.OK);
    /*
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setResponseCode(404);
            errorDetails.setTimeStamp(new Date());
            errorDetails.setMessage(driverNotFoundException.getMessage());
            errorDetails.setDetails("Use existing driver id");

            System.out.println(driverNotFoundException.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
*/
        }
    }



    /*
    @PostMapping(value = "/")
    public Optional createDriver(@RequestBody Driver driver){
        Optional<Driver> d = null;
        return driverService.createDriver(driver).orElse(d);



        return ResponseEntity.ok().body(d);
        return driverService.createDriver(driver).orElse(d);
        Optional<Driver> d = driverService.createDriver(driver);
        if(d.isPresent()){
            return ResponseEntity.ok().body(d);
        }
        else{
            d.orElseThrow(new ArithmeticException("janzz"));
        }
        return ResponseEntity.ok().body(driverService.createDriver(driver).get());
    }

     */



}
