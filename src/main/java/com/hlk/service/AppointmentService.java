/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service;

import com.hlk.model.Appointment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI GE66
 */
public interface AppointmentService {
    List<Appointment> getAppointments(String kw);
    Appointment getAppointmentById(int AppointmentId);
    List<Appointment> getAppointmentByPatient(int patientId);
    boolean deleteAppointment(int AppointmentId);
    boolean addOrUpdateAppointment(Appointment appointment);
    boolean updateNurseAppointment(int appointmentId,int nurseId);
    List<List<Map<Object,Object>>> getCountPatientByYear(int year);
    Map<Object, Object> getStatisticPatientByYear(int year);
}
