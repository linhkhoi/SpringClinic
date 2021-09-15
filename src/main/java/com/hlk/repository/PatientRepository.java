/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Patient;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface PatientRepository {
    List<Patient> getPatients(String kw);
    Patient getPatientById(int patientId);
    boolean deletePatient(int patientId);
    boolean addOrUpdatePatient(Patient patient);
    
    String getEmailPatientByAppointmentId(int id);
    
}
