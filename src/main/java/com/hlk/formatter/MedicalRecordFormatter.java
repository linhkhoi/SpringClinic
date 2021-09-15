/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.MedicalRecord;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class MedicalRecordFormatter implements Formatter<MedicalRecord>{

    @Override
    public String print(MedicalRecord object, Locale locale) {
        return object.toString();
    }

    @Override
    public MedicalRecord parse(String text, Locale locale) throws ParseException {
        MedicalRecord m = new MedicalRecord();
        m.setId(Integer.parseInt(text));
        return m;
    }
    
}
