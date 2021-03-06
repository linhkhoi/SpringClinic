/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.utils;

import com.hlk.model.Cart;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MSIGE66
 */
//public class Utils {
//    public static Map<String, BigDecimal> getCartStats(Map<Integer, Cart> cart) {
//        int quantity = 0;
//        BigDecimal totalAmount = new BigDecimal(0);
//        
//        if (cart != null) {
//            for (Cart c: cart.values()) {
//                quantity += c.getQuantity();
//                totalAmount = totalAmount.add(c.getPrice().multiply(new BigDecimal(c.getQuantity())));
//            }   
//        }
//        
//        Map<String, BigDecimal> result = new HashMap<>();
//        result.put("totalAmount", totalAmount);
//        result.put("totalQuantity", new BigDecimal(quantity));
//        
//        return result;
//    }
//}
public class Utils {
    public static int countCart(Map<Integer, Cart> cart){
        int q = 0;
        if(cart != null)
            for(Cart c: cart.values())
                q += c.getQuantity();
        
        return q;
    }
    
    public static Map<String, BigDecimal> cartStats(Map<Integer, Cart> cart){
        BigDecimal s = BigDecimal.ZERO;
        int q = 0;
        
        if(cart != null)
            for(Cart c: cart.values())
                s = s.add(c.getPrice().multiply(new BigDecimal(c.getQuantity()))) ;
        Map<String, BigDecimal> kq = new HashMap<>();
        kq.put("counter", new BigDecimal(q));
        kq.put("amount", s);
        
        return kq;
    }
}
