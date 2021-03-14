package com.janitha.trafficoffencemanagement.officerservice.controller;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import com.janitha.trafficoffencemanagement.officerservice.exception.ErrorDetails;
import com.janitha.trafficoffencemanagement.officerservice.exception.OfficerNotFoundException;
import com.janitha.trafficoffencemanagement.officerservice.service.OfficerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "services/officers")
public class OfficerController {

    Logger logger = LoggerFactory.getLogger(OfficerController.class);

    Response officerResponse = new Response();

    @Autowired
    OfficerService officerService;


    @PostMapping
    @PreAuthorize("hasAuthority('create_officer')")
    public ResponseEntity<Officer> createOfficer(@RequestBody Officer officer) {
        System.out.println("Request : createOfficer");
        return new ResponseEntity<Officer>(officerService.createOfficer(officer), HttpStatus.CREATED);
    }

    @PostMapping(value="/msg")
    public String message(){
        return "Hellooo";
    }


    @GetMapping(value = "{officerId}")
    @PreAuthorize("hasAuthority('read_officer')")
    public ResponseEntity<Response> getOfficerById(@PathVariable int officerId) {
        System.out.println("Request : getOfficerById");
        try {

            Officer officer = officerService.getOfficerById(officerId);
            officerResponse.setStatusCode(0);
            officerResponse.setMessage("Officer Found");
            officerResponse.setData(officer);

            return new ResponseEntity<Response>(officerResponse, HttpStatus.OK);

        } catch (OfficerNotFoundException officerNotFoundException) {

            logger.error(officerNotFoundException.getMessage());

            officerResponse.setStatusCode(-99);
            officerResponse.setMessage(officerNotFoundException.getMessage());
            officerResponse.setData(null);

            return new ResponseEntity(officerResponse, HttpStatus.OK);
        }
    }


    @GetMapping
    @PreAuthorize("hasAuthority('read_officer')")
    public ResponseEntity<Response> getAllOfficers() {
        System.out.println("Request : getAllOfficers");
        try {

            List<Officer> officer = officerService.getAllOfficers();
            officerResponse.setStatusCode(0);
            officerResponse.setMessage("Officers Found");
            officerResponse.setData(officer);

            return new ResponseEntity<Response>(officerResponse, HttpStatus.OK);

        }catch(OfficerNotFoundException officerNotFoundException) {

            logger.error(officerNotFoundException.getMessage());

            officerResponse.setStatusCode(-99);
            officerResponse.setMessage(officerNotFoundException.getMessage());
            officerResponse.setData(null);

            return new ResponseEntity<Response>(officerResponse, HttpStatus.OK);

        }
    }


    @PutMapping("{officerId}")
    @PreAuthorize("hasAuthority('update_officer')")
    public ResponseEntity<Response> updateOfficer(@RequestBody Officer officer, @PathVariable int officerId) {
        System.out.println("Request : updateOfficer");
        try {

            Officer updatedOfficer = officerService.updateOfficer(officer, officerId);
            officerResponse.setStatusCode(0);
            officerResponse.setMessage("Officer " +officerId +" Updated");
            officerResponse.setData(updatedOfficer);

            return new ResponseEntity<Response>(officerResponse, HttpStatus.OK);

        }catch(OfficerNotFoundException officerNotFoundException) {

            logger.error(officerNotFoundException.getMessage());

            officerResponse.setStatusCode(-99);
            officerResponse.setMessage(officerNotFoundException.getMessage());
            officerResponse.setData(null);

            return new ResponseEntity<Response>(officerResponse, HttpStatus.OK);

        }
    }


    @PatchMapping("{officerId}")
    @PreAuthorize("hasAuthority('update_officer')")
    public ResponseEntity<Officer> updateOfficerPhone(@RequestBody Officer officer, @PathVariable int officerId) {
        System.out.println("Request : updateOfficerPhone");
        try {
            return new ResponseEntity<Officer>(officerService.updateOfficer(officer, officerId), HttpStatus.OK);
        } catch (OfficerNotFoundException officerNotFoundException) {

            logger.error(officerNotFoundException.getMessage());

            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setResponseCode(404);
            errorDetails.setTimeStamp(new Date());
            errorDetails.setMessage(officerNotFoundException.getMessage());
            errorDetails.setDetails("Use existing officer id");

            System.out.println(officerNotFoundException.getMessage());
            return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

        }
    }


    @DeleteMapping("{officerId}")
    @PreAuthorize("hasAuthority('delete_officer')")
    public ResponseEntity deleteOfficer(@PathVariable int officerId){
        System.out.println("Request : deleteOfficer");
        try{
            officerService.deleteOfficer(officerId);
            System.out.println("Officer : " + officerId + " deleted successfully");
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }catch(OfficerNotFoundException officerNotFoundException){

            logger.error(officerNotFoundException.getMessage());

            officerResponse.setStatusCode(-99);
            officerResponse.setMessage(officerNotFoundException.getMessage());
            officerResponse.setData(null);

            return new ResponseEntity<Response>(officerResponse, HttpStatus.OK);
        }
    }

}

