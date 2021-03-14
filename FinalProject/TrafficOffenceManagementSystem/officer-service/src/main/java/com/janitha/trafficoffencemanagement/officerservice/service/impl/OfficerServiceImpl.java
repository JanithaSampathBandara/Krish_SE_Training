package com.janitha.trafficoffencemanagement.officerservice.service.impl;

import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import com.janitha.trafficoffencemanagement.officerservice.exception.OfficerNotFoundException;
import com.janitha.trafficoffencemanagement.officerservice.repository.OfficerRepository;
import com.janitha.trafficoffencemanagement.officerservice.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class OfficerServiceImpl implements OfficerService {

    @Autowired
    OfficerRepository officerRepository;

    @Override
    public Officer createOfficer(Officer officer) {
        return officerRepository.save(officer);
    }

    @Override
    public Officer getOfficerById(int officerId) {
        Optional<Officer> officer = officerRepository.findById(officerId);
        if(officer.isPresent()){
            return officer.get();
        }
        else{
            throw new OfficerNotFoundException("No officer found for this id : " + officerId);
        }
    }

    @Override
    public List<Officer> getAllOfficers() {
        List<Officer> officers =  officerRepository.findAll();
        if(!officers.isEmpty()){
            return officers;
        }
        else{
            throw new OfficerNotFoundException("No officers found");
        }
    }

    @Override
    public Officer updateOfficer(Officer officer, int officerId) {
        Optional<Officer> existingOfficer = officerRepository.findById(officerId);
        if(existingOfficer.isPresent()){
            existingOfficer.get().setName(officer.getName());
            existingOfficer.get().setAddress(officer.getAddress());
            existingOfficer.get().setNic(officer.getNic());
            existingOfficer.get().setPhone(officer.getPhone());
            existingOfficer.get().setEmail(officer.getEmail());
            existingOfficer.get().setGender(officer.getGender());
            existingOfficer.get().setDob(officer.getDob().toString()); // previous -> existingOfficer.get().setDob(officer.getDob());
            existingOfficer.get().setPassword(officer.getPassword());
            return officerRepository.save(existingOfficer.get());
        }
        else{
            throw new OfficerNotFoundException("No officer is found from this license no : " + officerId + " to update ");
        }
    }

    @Override
    public Officer updateOfficerPhone(Officer officer, int officerId) {
        Optional<Officer> existingOfficer = officerRepository.findById(officerId);
        if (existingOfficer.isPresent()) {

            existingOfficer.get().setPhone(officer.getPhone());

            return officerRepository.save(existingOfficer.get());
        }
        else{
            throw new OfficerNotFoundException("No officer is found from this license no : " + officerId + " to update ");
        }
    }

    @Override
    public void deleteOfficer(int officerId) {
        Optional<Officer> existingOfficer = officerRepository.findById(officerId);
        if(existingOfficer.isPresent()){
            officerRepository.delete(existingOfficer.get());
        }
        else{
            throw new OfficerNotFoundException("No officer for this id : " + officerId + " to delete");
        }
    }
}
