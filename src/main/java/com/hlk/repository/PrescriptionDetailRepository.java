/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.PrescriptionDetail;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface PrescriptionDetailRepository {
    List<PrescriptionDetail> getPrescriptionDetails(String kw);
    PrescriptionDetail getPrescriptionDetailById(int id);
    boolean deletePrescriptionDetail(int id);
    boolean addOrUpdatePrescriptionDetail(PrescriptionDetail prescriptionDetail);
}
