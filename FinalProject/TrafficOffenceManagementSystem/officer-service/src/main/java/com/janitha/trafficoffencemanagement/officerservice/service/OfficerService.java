package com.janitha.trafficoffencemanagement.officerservice.service;

import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;

import java.util.List;

public interface OfficerService {

    public Officer createOfficer(Officer officer);
    public Officer getOfficerById(int officerId);
    public List<Officer> getAllOfficers();
    public Officer updateOfficer(Officer officer, int officerId);
    public Officer updateOfficerPhone(Officer officer, int officerPhone);
    public void deleteOfficer(int officerId);

}
