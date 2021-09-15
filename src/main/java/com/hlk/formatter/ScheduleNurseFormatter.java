/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.ScheduleNurse;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class ScheduleNurseFormatter implements Formatter<ScheduleNurse>{

    @Override
    public String print(ScheduleNurse object, Locale locale) {
        return object.toString();
    }

    @Override
    public ScheduleNurse parse(String text, Locale locale) throws ParseException {
        ScheduleNurse s = new ScheduleNurse();
        s.setId(Integer.parseInt(text));
        return s;
    }
    
}
