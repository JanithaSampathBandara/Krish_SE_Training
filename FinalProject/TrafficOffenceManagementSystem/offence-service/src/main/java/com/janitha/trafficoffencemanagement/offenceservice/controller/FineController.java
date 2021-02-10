package com.janitha.trafficoffencemanagement.offenceservice.controller;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.offenceservice.dto.FineResponse;
import com.janitha.trafficoffencemanagement.offenceservice.exception.ErrorDetails;
import com.janitha.trafficoffencemanagement.offenceservice.exception.FineNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.exception.OffenceNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "services/fines")
public class FineController {

    FineResponse fineResponse = new FineResponse();

    @Autowired
    FineService fineService;

    @PostMapping
    public ResponseEntity<FineResponse> createFine(@RequestBody Fine fine){


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
    public ResponseEntity<FineResponse> getFineByLicenseNo(@PathVariable String licenseNo){
        try{
            List<Fine> fines = fineService.getFineByLicenseNo(licenseNo);
            fineResponse.setStatusCode(0);
            fineResponse.setMessage("Fine Found");
            fineResponse.setData(fines);

            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);

        }catch(FineNotFoundException fineNotFoundException){
            fineResponse.setStatusCode(-99);
            fineResponse.setMessage(fineNotFoundException.getMessage());
            fineResponse.setData(null);
            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);
        }
    }


    @PutMapping(value = "{licenseNo}")
    public ResponseEntity<FineResponse> updateFine(@RequestBody Fine fine, @PathVariable String licenseNo) {
        try {

            int updatedFine = fineService.updateFineStatus(licenseNo, fine.getStatus());
            fineResponse.setStatusCode(1);
            fineResponse.setMessage("Fine Updated");
            fineResponse.setData(fine.status);

            return new ResponseEntity<FineResponse>(fineResponse, HttpStatus.OK);

        } catch (FineNotFoundException fineNotFoundException) {
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
        }catch(FineNotFoundException fineNotFoundException){
            System.out.println(fineNotFoundException.getMessage());
            return null;
        }
    }



}
