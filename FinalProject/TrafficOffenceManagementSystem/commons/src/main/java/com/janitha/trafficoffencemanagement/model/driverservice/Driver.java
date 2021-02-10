package com.janitha.trafficoffencemanagement.model.driverservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @NotBlank(message = "license number is mandatory")
    private String licenseNo;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "NIC is mandatory")
    private String nic;
    @NotNull(message = "NIC is mandatory") // @NotBlank(message = "NIC is mandatory") //NotBlank only allowed for String values@NotBlank(message = "Phone is mandatory")
    private long phone;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    @NotNull(message = "Date of birth is mandatory") //@NotBlank(message = "Date of birth is mandatory")
    private Date dob;
    @NotBlank(message = "Password is mandatory")
    private String password;

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
