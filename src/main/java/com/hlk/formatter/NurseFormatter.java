/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Nurse;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class NurseFormatter implements Formatter<Nurse>{

    @Override
    public String print(Nurse object, Locale locale) {
        return object.toString();
    }

    @Override
    public Nurse parse(String text, Locale locale) throws ParseException {
        Nurse n = new Nurse();
        n.setId(Integer.parseInt(text));
        return n;
    }
    
}
