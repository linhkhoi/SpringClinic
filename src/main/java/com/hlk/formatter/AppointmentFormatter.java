/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Appointment;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class AppointmentFormatter implements Formatter<Appointment>{

    @Override
    public String print(Appointment object, Locale locale) {
        return object.toString();
    }

    @Override
    public Appointment parse(String text, Locale locale) throws ParseException {
        Appointment a = new Appointment();
        a.setId(Integer.parseInt(text));
        return a;
    }
    
}
