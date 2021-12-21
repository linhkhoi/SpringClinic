/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Doctor;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface DoctorRepository{
    List<Doctor> getDoctors(String kw);
    List<Doctor> getDoctors(String kw,int page);
    Doctor getDoctorById(int doctorId);
    long countDoctor(String kw);
    boolean deleteDoctor(int doctorId);
    boolean addOrUpdateDoctor(Doctor doctor);

    
}
