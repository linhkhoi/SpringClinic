/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Appointment;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface AppointmentRepository {
    List<Appointment> getAppointments(String kw);
    Appointment getAppointmentById(int AppointmentId);
    List<Appointment> getAppointmentByPatient(int patientId);
    boolean deleteAppointment(int AppointmentId);
    boolean addOrUpdateAppointment(Appointment appointment);
    boolean updateNurseAppointment(int appointmentId,int nurseId);
    List<Object[]> getCountPatientByYear(int year);
}
