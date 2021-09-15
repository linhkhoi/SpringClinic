/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.formatter;

import com.hlk.model.Order;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author MSIGE66
 */
public class OrderFormatter implements Formatter<Order>{

    @Override
    public String print(Order object, Locale locale) {
        return object.toString();
    }

    @Override
    public Order parse(String text, Locale locale) throws ParseException {
        Order o = new Order();
        o.setId(Integer.parseInt(text));
        return o;
    }
    
}
