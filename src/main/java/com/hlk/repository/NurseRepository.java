/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Appointment;
import com.hlk.model.Nurse;
import com.hlk.model.Prescription;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface NurseRepository {
    List<Nurse> getNurses(String kw);
    Nurse getNurseById(int nurseId);
    boolean deleteNurse(int nurseId);
    boolean addOrUpdateNurse(Nurse nurse);
    List<Prescription> getPrescriptionForOrder(String kw);
    List<Object[]> getDataForOrder(int preId);
    List<Appointment> getAppointmentForConfirm(String kw, boolean check);
}
