/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Cart;
import com.hlk.model.Prescription;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI GE66
 */
public interface PrescriptionRepository {
    List<Prescription> getPrescriptions(String kw);
    Prescription getPrescriptionById(int id);
    boolean deletePrescription(int id);
    boolean addOrUpdatePrescription(Prescription prescription);
    boolean addPrescription(Map<Integer, Cart> cart, int appointmentId, int doctorId);
    boolean updateIsPaid(int id);
    
}
