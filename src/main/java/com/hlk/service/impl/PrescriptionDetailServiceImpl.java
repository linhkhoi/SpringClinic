/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.PrescriptionDetail;
import com.hlk.repository.PrescriptionDetailRepository;
import com.hlk.service.PrescriptionDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class PrescriptionDetailServiceImpl implements PrescriptionDetailService{
    @Autowired
    private PrescriptionDetailRepository detailRepository;

    @Override
    public List<PrescriptionDetail> getPrescriptionDetails(String kw) {
        return this.detailRepository.getPrescriptionDetails(kw);
    }

    @Override
    public PrescriptionDetail getPrescriptionDetailById(int id) {
        return this.detailRepository.getPrescriptionDetailById(id);
    }

    @Override
    public boolean deletePrescriptionDetail(int id) {
        return this.detailRepository.deletePrescriptionDetail(id);
    }

    @Override
    public boolean addOrUpdatePrescriptionDetail(PrescriptionDetail prescriptionDetail) {
        return this.detailRepository.addOrUpdatePrescriptionDetail(prescriptionDetail);
    }
    
}
