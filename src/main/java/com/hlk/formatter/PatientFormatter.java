/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Patient;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class PatientFormatter implements Formatter<Patient>{

    @Override
    public String print(Patient object, Locale locale) {
        return object.toString();
    }

    @Override
    public Patient parse(String text, Locale locale) throws ParseException {
        Patient p = new Patient();
        p.setId(Integer.parseInt(text));
        return p;
    }
    
}
