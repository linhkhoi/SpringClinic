/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service;

import com.hlk.model.Appointment;
import com.hlk.model.Nurse;
import com.hlk.model.Prescription;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI GE66
 */
public interface NurseService {
    List<Nurse> getNurses(String kw);
    Nurse getNurseById(int nurseId);
    boolean deleteNurse(int nurseId);
    boolean addOrUpdateNurse(Nurse nurse);
    List<Prescription> getPrescriptionForOrder(String kw, Date createdDate,int page);
    long CountPrescriptionForOrder(String kw, Date createdDate);
    Map<Object, Object> getDetailOrder(int id);
     long countAppointmentForConfirm(String kw,Date fromDate, Date toDate, boolean check);
    List<Appointment> getAppointmentForConfirm(String kw,Date fromDate, Date toDate, int page, boolean check);
}
