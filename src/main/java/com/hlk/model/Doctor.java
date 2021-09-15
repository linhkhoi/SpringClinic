/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.model;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author MSI GE66
 */
@Entity
@Table(name = "clinic_doctor")
public class Doctor {
    @Id
    @Column(name = "user_id")
    private int id;
    
    private BigDecimal salary;
   
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescriptions;
    
    @OneToMany(mappedBy = "doctor")
    private Set<ScheduleDoctor> scheduleDoctors;

    public Set<ScheduleDoctor> getScheduleDoctors() {
        return scheduleDoctors;
    }

    public void setScheduleDoctors(Set<ScheduleDoctor> scheduleDoctors) {
        this.scheduleDoctors = scheduleDoctors;
    }
    
    

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
