/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.ScheduleNurse;
import com.hlk.repository.ScheduleNurseRepository;
import com.hlk.service.ScheduleNurseService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class ScheduleNurseServiceImpl implements ScheduleNurseService{
    @Autowired
    private ScheduleNurseRepository nurseRepository;

    @Override
    public List<ScheduleNurse> getScheduleNurses(String kw) {
        return this.nurseRepository.getScheduleNurses(kw);
    }

    @Override
    public ScheduleNurse getScheduleNurseById(int Id) {
        return this.nurseRepository.getScheduleNurseById(Id);
    }

    @Override
    public boolean deleteScheduleNurse(int Id) {
        return this.nurseRepository.deleteScheduleNurse(Id);
    }

    @Override
    public boolean addOrUpdateScheduleNurse(ScheduleNurse scheduleNurse) {
        return this.nurseRepository.addOrUpdateScheduleNurse(scheduleNurse);
    }

    @Override
    public List<ScheduleNurse> getScheduleNurseByNurse(int id) {
        return this.nurseRepository.getScheduleNurseByNurse(id);
    }

    @Override
    public List<ScheduleNurse> getScheduleNurseByNurse(int id, Date fromDate, Date toDate, int page) {
        return this.nurseRepository.getScheduleNurseByNurse(id, fromDate, toDate, page);
    }

    @Override
    public long countSchedule(int id, Date fromDate, Date toDate) {
        return this.nurseRepository.countSchedule(id, fromDate, toDate);
    }
    
}
