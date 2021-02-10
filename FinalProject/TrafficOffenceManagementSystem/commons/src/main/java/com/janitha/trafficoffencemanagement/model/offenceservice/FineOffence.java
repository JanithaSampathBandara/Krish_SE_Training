package com.janitha.trafficoffencemanagement.model.offenceservice;

import javax.persistence.Id;

public class FineOffence {

    @Id
    public int fineId;
    public int offenceId;
    public double fine;

    public FineOffence(int fineId, int offenceId, double fine) {
        this.fineId = fineId;
        this.offenceId = offenceId;
        this.fine = fine;
    }



}
