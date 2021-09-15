/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.Sick;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface SickRepository {
    List<Sick> getSicks(String kw);
    Sick getSickById(int id);
    boolean deleteSick(int id);
    boolean addOrUpdateSick(Sick sick);
}
