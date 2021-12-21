/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Doctor;
import com.hlk.model.Prescription;
import com.hlk.repository.DoctorRepository;
import com.hlk.service.DoctorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getDoctors(String kw) {
        return this.doctorRepository.getDoctors(kw);
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        return this.doctorRepository.getDoctorById(doctorId);
    }

    @Override
    public boolean deleteDoctor(int doctorId) {
        return this.doctorRepository.deleteDoctor(doctorId);
    }

    @Override
    public boolean addOrUpdateDoctor(Doctor doctor) {
        return this.doctorRepository.addOrUpdateDoctor(doctor);
    }

    @Override
    public List<Doctor> getDoctors(String kw, int page) {
        return this.doctorRepository.getDoctors(kw, page);
    }

    @Override
    public long countDoctor(String kw) {
        return this.doctorRepository.countDoctor(kw);
    }
    
    
}
