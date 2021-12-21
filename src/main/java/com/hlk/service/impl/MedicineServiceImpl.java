/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Medicine;
import com.hlk.repository.MedicineRepository;
import com.hlk.service.MedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class MedicineServiceImpl implements MedicineService{

    @Autowired
    private MedicineRepository medicineRepository;
    
    @Override
    public List<Medicine> getMedicines(String kw, int page) {
        return this.medicineRepository.getMedicines(kw, page);
    }

    @Override
    public Medicine getMedicineById(int medicineId) {
        return this.medicineRepository.getMedicineById(medicineId);
    }

    @Override
    public boolean deleteMedicine(int medicineId) {
        return this.medicineRepository.deleteMedicine(medicineId);
    }

    @Override
    public boolean addOrUpdateMedicine(Medicine medicine) {
        return this.medicineRepository.addOrUpdateMedicine(medicine);
    }

    @Override
    public List<Medicine> getMedicines(String kw) {
        return this.medicineRepository.getMedicines(kw);
    }

    @Override
    public long countMedicine(String kw) {
        return this.medicineRepository.countMedicine(kw);
    }

    @Override
    public List<Object[]> getMedicinesByPrescription(int preId) {
        return this.medicineRepository.getMedicinesByPrescription(preId);
    }
    
}
