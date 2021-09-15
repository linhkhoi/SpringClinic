/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Sick;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class SickFormatter implements Formatter<Sick>{

    @Override
    public String print(Sick s, Locale locale) {
        return s.toString();
    }

    @Override
    public Sick parse(String text, Locale locale) throws ParseException {
        Sick s = new Sick();
        s.setId(Integer.parseInt(text));
        return s;
    }
    
}
