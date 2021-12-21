/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.service.AppointmentService;
import com.hlk.service.MedicineService;
import com.hlk.service.SickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MSIGE66
 */
@RestController
@RequestMapping("/api")
public class ApiDeleteController {
    
    @Autowired
    private SickService sickService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private MedicineService medicineService;
    
    @DeleteMapping("/sick/{sickId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleleSick(@PathVariable(name = "sickId") int sickId) {
        this.sickService.deleteSick(sickId);
    }
    
    @DeleteMapping("/appointment/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleleAppointment(@PathVariable(name = "appId") int appId) {
        this.appointmentService.deleteAppointment(appId);
    }
    
    @DeleteMapping("/medicine/{medicineId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleleMedicine(@PathVariable(name = "medicineId") int medicineId) {
        this.medicineService.deleteMedicine(medicineId);
    }
}
