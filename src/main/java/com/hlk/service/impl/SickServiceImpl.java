/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service.impl;

import com.hlk.model.Sick;
import com.hlk.repository.SickRepository;
import com.hlk.service.SickService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI GE66
 */
@Service
public class SickServiceImpl implements SickService{
    @Autowired
    private SickRepository sickRepository;
    

    @Override
    public List<Sick> getSicks(String kw) {
        return this.sickRepository.getSicks(kw);
    }

    @Override
    public Sick getSickById(int i) {
        return this.sickRepository.getSickById(i);
    }

    @Override
    public boolean addOrUpdateSick(Sick sick) {
        return this.sickRepository.addOrUpdateSick(sick);
    }

    @Override
    public boolean deleteSick(int id) {
        return this.sickRepository.deleteSick(id);
    }
    
}
