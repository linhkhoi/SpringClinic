/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Doctor;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class DoctorFormatter implements Formatter<Doctor>{

    @Override
    public String print(Doctor object, Locale locale) {
        return object.toString();
    }

    @Override
    public Doctor parse(String text, Locale locale) throws ParseException {
        Doctor d = new Doctor();
        d.setId(Integer.parseInt(text));
        return d;
    }
    
}
