package com.janitha.trafficoffencemanagement.offenceservice.service;

import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;

import java.util.List;

public interface FineService {

    public Fine createFine(Fine fine);
    // public List<Offence> getAllOffences();
    public List<Fine> getFineByLicenseNo(String licenseNo);
    public int updateFineStatus(String licenseNo, String status);
    public List<Integer> getUnpaidOffenceList(String licenseNo);
    public Fine updateFine(Fine fine, int fineId);
    public List<Fine> getAllUnpaidFines();

}
