package com.janitha.trafficoffencemanagement.offenceservice.service.impl;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.offenceservice.config.Token;
import com.janitha.trafficoffencemanagement.offenceservice.controller.FineController;
import com.janitha.trafficoffencemanagement.offenceservice.controller.OffenceController;
import com.janitha.trafficoffencemanagement.offenceservice.exception.FineNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.exception.OffenceNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.repository.FineRepository;
import com.janitha.trafficoffencemanagement.offenceservice.service.FineService;
import com.janitha.trafficoffencemanagement.offenceservice.service.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {
    /*
        private final RestTemplate restTemplate;

        public FineServiceImpl(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }
    */
    @Autowired
    FineRepository fineRepository;

    @Autowired
    OffenceService offenceService;

/*
    //  RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
    //      return restTemplateBuilder.build();
    //   }

    @Autowired
    private RestTemplate restTemplate;
*/

    /* working
        @Bean(name="remoteRestTemplate")
       // @LoadBalanced
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }
        //  RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
        //      return restTemplateBuilder.build();
        //   }

        @Autowired
        @Qualifier(value = "remoteRestTemplate")
       // @LoadBalanced //no instance found for driver/localhost error will pop up for both port and app name
        private RestTemplate restTemplate;
    working
    */
    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Fine createFine(Fine fine) {

        System.out.println("create fine service impl");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", Token.getAccessToken());
        HttpEntity entity = new HttpEntity<>(headers);



        // validates the driver
        System.out.println("ddddddddddddd");
        //   Response driver = restTemplate.exchange("http://localhost:8192/services/drivers/"+fine.getLicenseNo(), HttpMethod.GET, entity, Response.class).getBody();
        Response driver = restTemplate.exchange("http://driver/services/drivers/"+fine.getLicenseNo(), HttpMethod.GET, entity, Response.class).getBody();
        //    Response driver = restTemplate.getForObject("http://Driver/services/drivers/" + fine.getLicenseNo(), Response.class);
        //    Response driver = restTemplate.getForObject("http://localhost:8192/services/drivers/"+fine.getLicenseNo(), Response.class);

        if(driver != null){
            System.out.println(driver);
        }
        System.out.println(driver.getData());
        if (driver.getStatusCode() == 0) {

            return fineRepository.save(fine);
        } else {

            return null;
        }

    }

    @Override
    public List<Fine> getFineByLicenseNo(String licenseNo) {
        Optional<List<Fine>> fine = fineRepository.findFineByLicenseNo(licenseNo);
        //  if(fine.isPresent()){
        if (!fine.get().isEmpty()) {
            return fine.get();
        } else {
            throw new FineNotFoundException("No fine found for this license number : " + licenseNo);
        }
    }

    @Override
    public int updateFineStatus(String licenseNo, String status) {
        int fine = fineRepository.updateFineStatus(licenseNo, status);
        if (fine >= 1) {
            return 0;
        } else {
            throw new FineNotFoundException("No fine found for this license number : " + licenseNo);
        }
    }

    @Override
    public List<Integer> getUnpaidOffenceList(String licenseNo) {
        List<Integer> notPaidOffences = fineRepository.getUnpaidOffenceList(licenseNo);
        if (notPaidOffences != null) {
            return notPaidOffences;
        } else {
            throw new OffenceNotFoundException("No offences found to be paid for this license number : " + licenseNo);
        }
    }

    @Override
    public Fine updateFine(Fine fine, int fineId) {
        Optional<Fine> existingFine = fineRepository.findById(fineId);
        if(existingFine.isPresent()){
            existingFine.get().setVehicleNo(fine.getVehicleNo());
            existingFine.get().setDateAndTimeOfOffence(fine.getDateAndTimeOfOffence());
            existingFine.get().setPlace(fine.getPlace());
            existingFine.get().setOffenceId(fine.getOffenceId());
            existingFine.get().setValidFrom(fine.getValidFrom());
            existingFine.get().setValidTo(fine.getValidTo());
            existingFine.get().setCourt(fine.getCourt());
            existingFine.get().setCourtDate(fine.getCourtDate());
            existingFine.get().setPoliceStation(fine.getPoliceStation());
            existingFine.get().setIssuingOfficer(fine.getIssuingOfficer());

            return fineRepository.save(existingFine.get());
        }
        else{
            throw new OffenceNotFoundException("No fine is found from this fine Id : " + fineId + " to update ");
        }
    }

    @Override
    public List<Fine> getAllUnpaidFines() {
        List<Fine> unpaidFines = fineRepository.getAllUnpaidFines();
        if (unpaidFines != null) {
            return unpaidFines;
        } else {
            throw new FineNotFoundException("No fines found to be paid ");
        }
    }

    /*   @Override
    public Fine updateFineStatus(Fine fine, String licenseNo) {
        Optional<Fine> existingfine = fineRepository.findFineByLicenseNo(licenseNo);
        if(existingfine.isPresent()){
            existingfine.get().setLicenseNo(fine.getLicenseNo());
            existingfine.get().setNic(fine.getNic());
            existingfine.get().setDateAndTimeOfOffence(fine.getDateAndTimeOfOffence());
            existingfine.get().setPlace(fine.getPlace());
            existingfine.get().setOffenceId(fine.getOffenceId());
            existingfine.get().setValidFrom(fine.getValidFrom());
            existingfine.get().setValidTo(fine.getValidTo());
            existingfine.get().setCourt(fine.getCourt());
            existingfine.get().setCourtDate(fine.getCourtDate());
            existingfine.get().setPoliceStation(fine.getPoliceStation());
            existingfine.get().setIssuingOfficer(fine.getIssuingOfficer());
            existingfine.get().setStatus(fine.getStatus());

            return fineRepository.save(existingfine.get());
        }
        else{
            throw new FineNotFoundException("No fine is found from this license number : " + licenseNo + " to update ");
        }
    }
*/


}
