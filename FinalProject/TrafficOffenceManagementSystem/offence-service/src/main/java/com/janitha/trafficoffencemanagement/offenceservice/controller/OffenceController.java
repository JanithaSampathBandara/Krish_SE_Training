package com.janitha.trafficoffencemanagement.offenceservice.controller;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import com.janitha.trafficoffencemanagement.offenceservice.exception.ErrorDetails;
import com.janitha.trafficoffencemanagement.offenceservice.exception.OffenceNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.service.OffenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "services/offences")
public class OffenceController {

    Logger logger = LoggerFactory.getLogger(OffenceController.class);

    Response response = new Response();

    @Autowired
    OffenceService offenceService;

    @PostMapping
    public ResponseEntity<Offence> createOffence(@RequestBody Offence offence){
        return new ResponseEntity<Offence>(offenceService.createOffence(offence), HttpStatus.CREATED);
    }

    @GetMapping(value = "{offenceId}")
    public ResponseEntity getOffenceById(@PathVariable int offenceId){
        System.out.println("getOffenceById");
        try{

            return new ResponseEntity<Offence>(offenceService.getOffenceById(offenceId), HttpStatus.OK);
    /*        Offence offence = offenceService.getOffenceById(offenceId);
            response.setStatusCode(0);
            response.setMessage("Offence Found");
            response.setData(offence);

            return new ResponseEntity<Response>(response, HttpStatus.OK);
*/
        }catch(OffenceNotFoundException offenceNotFoundException){

            logger.error(offenceNotFoundException.getMessage());

            response.setStatusCode(0);
            response.setMessage(offenceNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllOffences(){
        try{

            List<Offence> offences = offenceService.getAllOffences();
            response.setStatusCode(0);
            response.setMessage("Offences Found");
            response.setData(offences);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }catch(OffenceNotFoundException offenceNotFoundException){

            logger.error(offenceNotFoundException.getMessage());

            response.setStatusCode(-99);
            response.setMessage(offenceNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @PutMapping("{offenceId}")
    public ResponseEntity<Response> updateOffence(@RequestBody Offence offence, @PathVariable int offenceId) {
        try {

            Offence updatedOffence = offenceService.updateOffence(offence, offenceId);
            response.setStatusCode(0);
            response.setMessage("Offence Id : "+offenceId+ " Updated");
            response.setData(updatedOffence);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        } catch (OffenceNotFoundException offenceNotFoundException) {

            logger.error(offenceNotFoundException.getMessage());

            response.setStatusCode(0);
            response.setMessage(offenceNotFoundException.getMessage());
            response.setData(null);

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }
    }

    @DeleteMapping("{offenceId}")
    public ResponseEntity deleteOffence(@PathVariable int offenceId){
        try{
            offenceService.deleteOffence(offenceId);
       //     System.out.println("Offence : " + offenceId + " deleted successfully");
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }catch(OffenceNotFoundException offenceNotFoundException){

            logger.error(offenceNotFoundException.getMessage());

            response.setStatusCode(-99);
            response.setMessage(offenceNotFoundException.getMessage());
            response.setData(null);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }


}
