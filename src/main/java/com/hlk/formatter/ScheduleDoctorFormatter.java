/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.ScheduleDoctor;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class ScheduleDoctorFormatter implements Formatter<ScheduleDoctor>{

    @Override
    public String print(ScheduleDoctor object, Locale locale) {
        return object.toString();
    }

    @Override
    public ScheduleDoctor parse(String text, Locale locale) throws ParseException {
        ScheduleDoctor s = new ScheduleDoctor();
        s.setId(Integer.parseInt(text));
        return s;
    }
    
}
