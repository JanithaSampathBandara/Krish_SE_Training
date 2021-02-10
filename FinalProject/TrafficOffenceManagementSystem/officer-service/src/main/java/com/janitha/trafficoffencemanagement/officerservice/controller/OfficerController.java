package com.janitha.trafficoffencemanagement.officerservice.controller;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import com.janitha.trafficoffencemanagement.officerservice.exception.ErrorDetails;
import com.janitha.trafficoffencemanagement.officerservice.exception.OfficerNotFoundException;
import com.janitha.trafficoffencemanagement.officerservice.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "services/officers")
public class OfficerController {

    Response response = new Response();

    @Autowired
    OfficerService officerService;

    @PostMapping
    public ResponseEntity<Officer> createOfficer(@RequestBody Officer officer) {
        return new ResponseEntity<Officer>(officerService.createOfficer(officer), HttpStatus.CREATED);
    }

    @GetMapping(value = "{officerId}")
    public ResponseEntity<Response> getOfficerById(@PathVariable int officerId) {
        try {

            Officer officer = officerService.getOfficerById(officerId);
            response.setStatusCode(0);
            response.setMessage("Officer Found");
            response.setData(officer);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        } catch (OfficerNotFoundException officerNotFoundException) {

            response.setStatusCode(-99);
            response.setMessage(officerNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllOfficers() {
        try {

            List<Officer> officer = officerService.getAllOfficers();
            response.setStatusCode(0);
            response.setMessage("Officers Found");
            response.setData(officer);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }catch(OfficerNotFoundException officerNotFoundException) {
            response.setStatusCode(-99);
            response.setMessage(officerNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }
    }

    @PutMapping("{officerId}")
    public ResponseEntity<Response> updateOfficer(@RequestBody Officer officer, @PathVariable int officerId) {
        try {

            Officer updatedOfficer = officerService.updateOfficer(officer, officerId);
            response.setStatusCode(0);
            response.setMessage("Officer " +officerId +" Updated");
            response.setData(updatedOfficer);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }catch(OfficerNotFoundException officerNotFoundException) {

            response.setStatusCode(-99);
            response.setMessage(officerNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }
    }

    @PatchMapping("{officerId}")
    public ResponseEntity<Officer> updateOfficerPhone(@RequestBody Officer officer, @PathVariable int officerId) {
        try {
            return new ResponseEntity<Officer>(officerService.updateOfficer(officer, officerId), HttpStatus.OK);
        } catch (OfficerNotFoundException officerNotFoundException) {
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
    public ResponseEntity deleteOfficer(@PathVariable int officerId){
        try{
            officerService.deleteOfficer(officerId);
            System.out.println("Officer : " + officerId + " deleted successfully");
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }catch(OfficerNotFoundException officerNotFoundException){

            response.setStatusCode(-99);
            response.setMessage(officerNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

}

