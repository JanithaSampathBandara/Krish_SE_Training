package com.janitha.trafficoffencemanagement.offenceservice.service;

import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;

import java.util.List;

public interface OffenceService {

    public Offence createOffence(Offence offence);
    public Offence getOffenceById(int offenceId);
    public List<Offence> getAllOffences();
    public Offence updateOffence(Offence offence, int offenceId);
    public void deleteOffence(int offenceId);

}
