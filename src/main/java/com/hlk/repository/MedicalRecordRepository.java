/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.MedicalRecord;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSIGE66
 */
public interface MedicalRecordRepository {
    List<MedicalRecord> getMedicalRecords(String kw);
    List<MedicalRecord> getMedicalRecordsByPatient(int patientId);
    List<MedicalRecord> getMedicalRecordsByPatient(int patientId,Date fromDate, Date toDate, int page);
    long countMedicalRecord(int patientId,Date fromDate, Date toDate);
    MedicalRecord getMedicalRecordById(int medicalRecordId);
    boolean deleteMedicalRecord(int medicalRecordId);
    boolean addOrUpdateMedicalRecord(MedicalRecord medicalRecord);
}
