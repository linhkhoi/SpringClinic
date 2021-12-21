/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service;

import com.hlk.model.MedicalRecord;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSIGE66
 */
public interface MedicalRecordService {
    List<MedicalRecord> getMedicalRecords(String kw);
    MedicalRecord getMedicalRecordById(int medicalRecordId);
    List<MedicalRecord> getMedicalRecordsByPatient(int patientId);
    List<MedicalRecord> getMedicalRecordsByPatient(int patientId,Date fromDate, Date toDate, int page);
    long countMedicalRecord(int patientId,Date fromDate, Date toDate);
    boolean deleteMedicalRecord(int medicalRecordId);
    boolean addOrUpdateMedicalRecord(MedicalRecord medicalRecord);
}
