/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Donars {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String mobile;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String status;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfFirstDonation;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column
    private String createdBy;
    @Column
    private String modifiedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfFirstDonation() {
        return dateOfFirstDonation;
    }

    public void setDateOfFirstDonation(Date dateOfFirstDonation) {
        this.dateOfFirstDonation = dateOfFirstDonation;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    

}
