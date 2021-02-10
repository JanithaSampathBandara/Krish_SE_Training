package com.janitha.trafficoffencemanagement.model.offenceservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="offence")
public class Offence {

    @Id
    private int offenceId;
    private String offence;
    private BigDecimal fine;


 //   @ManyToOne(cascade = CascadeType.ALL)
 //   @JoinColumn(name = "project_code") // @Column is for normal fields and @JoinColum is for customize Foreignkey fields
 //   private Project project;


    public int getOffenceId() {
        return offenceId;
    }

    public void setOffenceId(int offenceId) {
        this.offenceId = offenceId;
    }

    public String getOffence() {
        return offence;
    }

    public void setOffence(String offence) {
        this.offence = offence;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }
}
