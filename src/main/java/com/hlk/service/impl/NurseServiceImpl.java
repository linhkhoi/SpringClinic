/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Nurse;
import com.hlk.model.Prescription;
import com.hlk.repository.NurseRepository;
import com.hlk.service.NurseService;
import java.math.BigDecimal;
import java.util.Date;
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
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public List<Nurse> getNurses(String kw) {
        return this.nurseRepository.getNurses(kw);
    }

    @Override
    public Nurse getNurseById(int nurseId) {
        return this.nurseRepository.getNurseById(nurseId);
    }

    @Override
    public boolean deleteNurse(int nurseId) {
        return this.nurseRepository.deleteNurse(nurseId);
    }

    @Override
    public boolean addOrUpdateNurse(Nurse nurse) {
        return this.nurseRepository.addOrUpdateNurse(nurse);
    }

    @Override
    public List<Prescription> getPrescriptionForOrder(String kw, Date createdDate,int page) {
        return this.nurseRepository.getPrescriptionForOrder(kw,createdDate,page);
    }

    @Override
    public Map<Object, Object> getDetailOrder(int i) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        BigDecimal total = new BigDecimal(0);
        List<Object[]> rs = this.nurseRepository.getDataForOrder(i);
        for (Object[] obj : rs) {
            map.put("expense", obj[0]);
            map.put("totalPrice", obj[1]);
            map.put("id", obj[2]);
            total = total.add((BigDecimal) obj[0]);
            total = total.add((BigDecimal) obj[1]);
        }
        map.put("total", total.longValue());

        return map;
    }

    @Override
    public List<Appointment> getAppointmentForConfirm(String string,Date fromDate, Date toDate, int page, boolean check) {
        return this.nurseRepository.getAppointmentForConfirm(string,fromDate,toDate, page, check);
    }

    @Override
    public long CountPrescriptionForOrder(String kw, Date createdDate) {
        return this.nurseRepository.CountPrescriptionForOrder(kw, createdDate);
    }

    @Override
    public long countAppointmentForConfirm(String kw, Date fromDate, Date toDate, boolean check) {
        return this.nurseRepository.countAppointmentForConfirm(kw, fromDate, toDate, check);
    }

}
