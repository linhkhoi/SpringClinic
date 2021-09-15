/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Appointment;
import com.hlk.repository.AppointmentRepository;
import com.hlk.service.AppointmentService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAppointments(String kw) {
        return this.appointmentRepository.getAppointments(kw);
    }

    @Override
    public Appointment getAppointmentById(int AppointmentId) {
        return this.appointmentRepository.getAppointmentById(AppointmentId);
    }

    @Override
    public boolean deleteAppointment(int AppointmentId) {
        return this.appointmentRepository.deleteAppointment(AppointmentId);
    }

    @Override
    public boolean addOrUpdateAppointment(Appointment appointment) {
        return this.appointmentRepository.addOrUpdateAppointment(appointment);
    }
    
    @Override
    public List<List<Map<Object, Object>>> getCountPatientByYear(int year) {
        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();
        
        List<Object[]> rs = this.appointmentRepository.getCountPatientByYear(year);
         for (Object[] obj: rs) {
            map = new HashMap<Object,Object>(); 
            map.put("x", obj[0]); 
            map.put("y", obj[1]);
            dataPoints1.add(map);
        }
        list.add(dataPoints1);
        
        return list;
    }

    @Override
    public Map<Object, Object> getStatisticPatientByYear(int year) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Integer max=0, min=Integer.MAX_VALUE, total=0;
        
        List<Object[]> rs = this.appointmentRepository.getCountPatientByYear(year);
        for (Object[] obj: rs) {
            if(max <(int) (long) obj[1]){
                max = (int) (long) obj[1];
                map.put("max", obj[1]); 
                map.put("maxMonth", obj[0]);
            }
            if(min > (int) (long) obj[1]){
                min = (int) (long) obj[1];
                map.put("min", obj[1]); 
                map.put("minMonth", obj[0]);
            }
            total += (int) (long) obj[1];
        }
        map.put("total", total);
        
        return map;
        
    }

    @Override
    public boolean updateNurseAppointment(int appointmentId, int nurseId) {
        return this.appointmentRepository.updateNurseAppointment(appointmentId, nurseId);
    }

    @Override
    public List<Appointment> getAppointmentByPatient(int patientId) {
        return this.appointmentRepository.getAppointmentByPatient(patientId);
    }
    
}
