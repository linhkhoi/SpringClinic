/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.MedicalRecord;
import com.hlk.repository.MedicalRecordRepository;
import com.hlk.service.MedicalRecordService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSIGE66
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService{
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecord> getMedicalRecords(String kw) {
        return this.medicalRecordRepository.getMedicalRecords(kw);
    }

    @Override
    public MedicalRecord getMedicalRecordById(int medicalRecordId) {
        return this.medicalRecordRepository.getMedicalRecordById(medicalRecordId);
    }

    @Override
    public boolean deleteMedicalRecord(int medicalRecordId) {
        return this.medicalRecordRepository.deleteMedicalRecord(medicalRecordId);
    }

    @Override
    public boolean addOrUpdateMedicalRecord(MedicalRecord medicalRecord) {
        return this.medicalRecordRepository.addOrUpdateMedicalRecord(medicalRecord);
    }
    
}
