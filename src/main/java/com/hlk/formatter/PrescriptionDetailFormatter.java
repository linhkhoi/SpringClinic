/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.PrescriptionDetail;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class PrescriptionDetailFormatter implements Formatter<PrescriptionDetail>{

    @Override
    public String print(PrescriptionDetail object, Locale locale) {
        return object.toString();
    }

    @Override
    public PrescriptionDetail parse(String text, Locale locale) throws ParseException {
        PrescriptionDetail p = new PrescriptionDetail();
        p.setId(Integer.parseInt(text));
        return p;
    }
    
}
