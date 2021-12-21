/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service;

import com.hlk.model.ScheduleNurse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface ScheduleNurseService {
    List<ScheduleNurse> getScheduleNurses(String kw);
    ScheduleNurse getScheduleNurseById(int Id);
    List<ScheduleNurse> getScheduleNurseByNurse(int id);
    List<ScheduleNurse> getScheduleNurseByNurse(int id,Date fromDate, Date toDate, int page);
    long countSchedule(int id,Date fromDate, Date toDate);
    boolean deleteScheduleNurse(int Id);
    boolean addOrUpdateScheduleNurse(ScheduleNurse scheduleNurse);
}
