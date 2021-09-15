/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.MedicalRecord;
import java.util.List;

/**
 *
 * @author MSIGE66
 */
public interface MedicalRecordRepository {
    List<MedicalRecord> getMedicalRecords(String kw);
    MedicalRecord getMedicalRecordById(int medicalRecordId);
    boolean deleteMedicalRecord(int medicalRecordId);
    boolean addOrUpdateMedicalRecord(MedicalRecord medicalRecord);
}
