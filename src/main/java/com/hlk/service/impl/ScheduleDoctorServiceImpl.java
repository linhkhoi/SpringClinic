/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.ScheduleDoctor;
import com.hlk.repository.ScheduleDoctorRepository;
import com.hlk.service.ScheduleDoctorService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class ScheduleDoctorServiceImpl implements ScheduleDoctorService{
    @Autowired
    private ScheduleDoctorRepository doctorRepository;

    @Override
    public List<ScheduleDoctor> getScheduleDoctors(String kw) {
        return this.doctorRepository.getScheduleDoctors(kw);
    }

    @Override
    public ScheduleDoctor getScheduleDoctorById(int Id) {
        return this.doctorRepository.getScheduleDoctorById(Id);
    }

    @Override
    public boolean deleteScheduleDoctor(int Id) {
        return this.doctorRepository.deleteScheduleDoctor(Id);
    }

    @Override
    public boolean addOrUpdateScheduleDoctor(ScheduleDoctor scheduleDoctor) {
        return this.doctorRepository.addOrUpdateScheduleDoctor(scheduleDoctor);
    }

    @Override
    public List<ScheduleDoctor> getScheduleDoctorByDoctor(int id) {
        return this.doctorRepository.getScheduleDoctorByDoctor(id);
    }

    @Override
    public List<ScheduleDoctor> getScheduleDoctorByDoctor(int id, Date fromDate, Date toDate, int page) {
        return this.doctorRepository.getScheduleDoctorByDoctor(id, fromDate, toDate, page);
    }

    @Override
    public long countSchedule(int id, Date fromDate, Date toDate) {
        return this.doctorRepository.countSchedule(id, fromDate, toDate);
    }
    
}
