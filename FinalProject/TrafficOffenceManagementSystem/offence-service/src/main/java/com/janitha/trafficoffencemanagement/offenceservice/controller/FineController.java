package com.janitha.trafficoffencemanagement.offenceservice.controller;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.offenceservice.dto.FineResponse;
import com.janitha.trafficoffencemanagement.offenceservice.exception.ErrorDetails;
import com.janitha.trafficoffencemanagement.offenceservice.exception.FineNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.exception.OffenceNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.service.FineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "services/fines")
public class FineController {

    Logger logger = LoggerFactory.getLogger(FineController.class);

    FineResponse fineResponse = new FineResponse();

    @Autowired
    FineService fineService;

    @PostMapping
    @PreAuthorize("hasAuthority('create_fine')")
    public ResponseEntity<FineResponse> createFine(@RequestBody Fine fine){

        System.out.println("create fine controller");

        Fine createdFine = fineService.createFine(fine);
        if(createdFine != null){
            fineResponse.setStatusCode(0);
            fineResponse.setMessage("Resource created");
            fineResponse.setData(createdFine);
            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);
        }
        else{
            fineResponse.setStatusCode(-99);
            fineResponse.setMessage("No driver found for this id ");
            fineResponse.setData(null);
            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);
        }

    }

    @GetMapping(value = "{licenseNo}")
    @PreAuthorize("hasAuthority('read_fine')")
    public ResponseEntity<FineResponse> getFineByLicenseNo(@PathVariable String licenseNo){
        try{
            List<Fine> fines = fineService.getFineByLicenseNo(licenseNo);
            fineResponse.setStatusCode(0);
            fineResponse.setMessage("Fine Found");
            fineResponse.setData(fines);

            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);

        }catch(FineNotFoundException fineNotFoundException){

            logger.error(fineNotFoundException.getMessage());

            fineResponse.setStatusCode(-99);
            fineResponse.setMessage(fineNotFoundException.getMessage());
            fineResponse.setData(null);
            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);
        }
    }

    @PutMapping("{fineId}")
    public ResponseEntity updateFine(@RequestBody Fine fine, @PathVariable int fineId) {
        try {

            Fine updatedFine = fineService.updateFine(fine, fineId);
          //  fineResponse.setStatusCode(0);
          //  fineResponse.setMessage("Fine Id : "+fineId+ " Updated");
          //  fineResponse.setData(updatedFine);

            return new ResponseEntity<Fine>(updatedFine, HttpStatus.OK);

        } catch (FineNotFoundException fineNotFoundException) {
         //   response.setStatusCode(0);
          //  response.setMessage(offenceNotFoundException.getMessage());
         //   response.setData(null);

            logger.error(fineNotFoundException.getMessage());

            return new ResponseEntity<Integer>(-99, HttpStatus.OK);

        }
    }

    @PutMapping(value = "/status/{licenseNo}")
    public ResponseEntity<FineResponse> updateFine(@RequestBody Fine fine, @PathVariable String licenseNo) {
        System.out.println("updateFinestatus()");
        try {

            int updatedFine = fineService.updateFineStatus(licenseNo, fine.getStatus());
            fineResponse.setStatusCode(1);
            fineResponse.setMessage("Fine Updated");
            fineResponse.setData(updatedFine);

            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);

        } catch (FineNotFoundException fineNotFoundException) {

            logger.error(fineNotFoundException.getMessage());

            fineResponse.setStatusCode(-99);
            fineResponse.setMessage(fineNotFoundException.getMessage());
            fineResponse.setData(null);

            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);

        }

    }

    // not paid fines should be generated
    @GetMapping(value="{licenseNo}/status")
    public List<Integer> getUnpaidOffenceList(@PathVariable String licenseNo){
        try{
            List<Integer> unpaidFines = fineService.getUnpaidOffenceList(licenseNo);
            return unpaidFines;
        }catch(OffenceNotFoundException offenceNotFoundException){

            logger.error(offenceNotFoundException.getMessage());

         //   System.out.println(offenceNotFoundException.getMessage());
            return null;
        }
    }
    @GetMapping(value="unpaid")
    public List<Fine> getAllUnpaidFines(){
        try{
            List<Fine> unpaid = fineService.getAllUnpaidFines();
            return unpaid;
        }catch(FineNotFoundException fineNotFoundException){

            logger.error(fineNotFoundException.getMessage());
       //     System.out.println(fineNotFoundException.getMessage());
            return null;
        }
    }

}
