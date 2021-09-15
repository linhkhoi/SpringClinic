/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Patient;
import com.hlk.repository.PatientRepository;
import com.hlk.service.PatientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getPatients(String kw) {
        return this.patientRepository.getPatients(kw);
    }

    @Override
    public Patient getPatientById(int patientId) {
        return this.patientRepository.getPatientById(patientId);
    }

    @Override
    public boolean deletePatient(int patientId) {
        return this.patientRepository.deletePatient(patientId);
    }

    @Override
    public boolean addOrUpdatePatient(Patient patient) {
        return this.patientRepository.addOrUpdatePatient(patient);
    }

    @Override
    public String getEmailPatientByAppointmentId(int id) {
        return this.patientRepository.getEmailPatientByAppointmentId(id);
    }

   
    
}
