/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Prescription;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class PrescriptionFormatter implements Formatter<Prescription>{

    @Override
    public String print(Prescription object, Locale locale) {
        return object.toString();
    }

    @Override
    public Prescription parse(String text, Locale locale) throws ParseException {
        Prescription p = new Prescription();
        p.setId(Integer.parseInt(text));
        return p;
    }
    
}
