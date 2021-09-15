/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Medicine;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class MedicineFormatter implements Formatter<Medicine>{

    @Override
    public String print(Medicine object, Locale locale) {
        return object.toString();
    }

    @Override
    public Medicine parse(String text, Locale locale) throws ParseException {
        Medicine m = new Medicine();
        m.setId(Integer.parseInt(text));
        return m;
    }
    
}
