/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Cart;
import com.hlk.model.Prescription;
import com.hlk.repository.PrescriptionRepository;
import com.hlk.service.PrescriptionService;
import java.util.ArrayList;
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
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public List<Prescription> getPrescriptions(String kw) {
        return this.prescriptionRepository.getPrescriptions(kw);
    }

    @Override
    public Prescription getPrescriptionById(int id) {
        return this.prescriptionRepository.getPrescriptionById(id);
    }

    @Override
    public boolean deletePrescription(int id) {
        return this.prescriptionRepository.deletePrescription(id);
    }

    @Override
    public boolean addOrUpdatePrescription(Prescription prescription) {
        return this.prescriptionRepository.addOrUpdatePrescription(prescription);
    }

    @Override
    public boolean updateIsPaid(int id) {
        return this.prescriptionRepository.updateIsPaid(id);
    }

    @Override
    public boolean addPrescription(Map<Integer, Cart> cart, int appointmentId, int doctorId) {
        return this.prescriptionRepository.addPrescription(cart, appointmentId, doctorId);
    }

    @Override
    public List<Prescription> getPrescriptions(String kw, Date createdDate, int page) {
        return this.prescriptionRepository.getPrescriptions(kw, createdDate, page);
    }

    @Override
    public long countPrescription(String kw, Date createdDate) {
        return this.prescriptionRepository.countPrescription(kw, createdDate);
    }

    

}
