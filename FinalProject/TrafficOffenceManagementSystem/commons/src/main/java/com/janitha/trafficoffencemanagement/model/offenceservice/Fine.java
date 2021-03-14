package com.janitha.trafficoffencemanagement.model.offenceservice;

import com.janitha.trafficoffencemanagement.model.officerservice.Officer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fine")
public class Fine {

    @Id
    @GeneratedValue
    private int fineId;
    private String licenseNo;
    private String nic;
    private String vehicleNo;
    private LocalDate dateAndTimeOfOffence;
    public String place;
    public int offenceId; // In frontend there will be offence description and offencdId both in a dropdown list
    public LocalDate validFrom;
    public LocalDate validTo;
    public String court;
    public LocalDate courtDate;
    public String policeStation;
    public int issuingOfficer;
    public String status; //paid or not-paid

    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public LocalDate getDateAndTimeOfOffence() {
        return dateAndTimeOfOffence;
    }

    public void setDateAndTimeOfOffence(LocalDate dateAndTimeOfOffence) {
        this.dateAndTimeOfOffence = dateAndTimeOfOffence;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getOffenceId() {
        return offenceId;
    }

    public void setOffenceId(int offenceId) {
        this.offenceId = offenceId;
    }



    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public LocalDate getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(LocalDate courtDate) {
        this.courtDate = courtDate;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public int getIssuingOfficer() {
        return issuingOfficer;
    }

    public void setIssuingOfficer(int issuingOfficer) {
        this.issuingOfficer = issuingOfficer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    @OneToMany(mappedBy = "fine", cascade = CascadeType.ALL)
    //   private List<Offence> offences;


}
