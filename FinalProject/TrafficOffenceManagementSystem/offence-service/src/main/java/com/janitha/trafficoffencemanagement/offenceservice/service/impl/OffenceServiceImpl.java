package com.janitha.trafficoffencemanagement.offenceservice.service.impl;

import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import com.janitha.trafficoffencemanagement.offenceservice.exception.OffenceNotFoundException;
import com.janitha.trafficoffencemanagement.offenceservice.repository.OffenceRepository;
import com.janitha.trafficoffencemanagement.offenceservice.service.OffenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffenceServiceImpl implements OffenceService {

    @Autowired
    OffenceRepository offenceRepository;

    @Override
    public Offence createOffence(Offence offence) {
        return offenceRepository.save(offence);
    }

    @Override
    public Offence getOffenceById(int offenceId) {
        Optional<Offence> offence = offenceRepository.findById(offenceId);
        if(offence.isPresent()){
            return offence.get();
        }
        else{
            throw new OffenceNotFoundException("No offence found for this Id : " + offenceId);
        }
    }

    @Override
    public List<Offence> getAllOffences() {
        List<Offence> offences = offenceRepository.findAll();
        if(!offences.isEmpty()){
            return offences;
        }
        else{
            throw new OffenceNotFoundException("No offences found");
        }
    }

    @Override
    public Offence updateOffence(Offence offence, int offenceId) {
        Optional<Offence> existingOffence = offenceRepository.findById(offenceId);
        if(existingOffence.isPresent()){
            existingOffence.get().setOffence(offence.getOffence());
            existingOffence.get().setFine(offence.getFine());

            return offenceRepository.save(existingOffence.get());
        }
        else{
            throw new OffenceNotFoundException("No offence is found from this offence Id : " + offenceId + " to update ");
        }
    }

    @Override
    public void deleteOffence(int offenceId) {

        Optional<Offence> existingOffence = offenceRepository.findById(offenceId);
        if(existingOffence.isPresent()){
            offenceRepository.delete(existingOffence.get());
        }
        else{
            throw new OffenceNotFoundException("No offence for this id : " + offenceId + " to delete");
        }


    }
}
