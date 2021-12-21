/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Medicine;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface MedicineRepository {
    List<Medicine> getMedicines(String kw);
    List<Medicine> getMedicines(String kw, int page);
    List<Object[]> getMedicinesByPrescription(int preId);
    long countMedicine(String kw);
    Medicine getMedicineById(int medicineId);
    boolean deleteMedicine(int medicineId);
    boolean addOrUpdateMedicine(Medicine medicine);
}
