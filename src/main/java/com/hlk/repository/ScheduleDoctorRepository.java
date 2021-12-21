/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.ScheduleDoctor;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface ScheduleDoctorRepository {
    List<ScheduleDoctor> getScheduleDoctors(String kw);
    List<ScheduleDoctor> getScheduleDoctorByDoctor(int id);
    List<ScheduleDoctor> getScheduleDoctorByDoctor(int id,Date fromDate, Date toDate, int page);
    long countSchedule(int id,Date fromDate, Date toDate);
    ScheduleDoctor getScheduleDoctorById(int Id);
    boolean deleteScheduleDoctor(int Id);
    boolean addOrUpdateScheduleDoctor(ScheduleDoctor scheduleDoctor);
}
