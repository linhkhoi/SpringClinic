/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Cart;
import com.hlk.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author MSIGE66
 */
@Controller
public class CartController {
    
    @GetMapping("/cart")
    public String cart(Model model, HttpSession session){
        
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart != null){
            model.addAttribute("carts", cart.values());
            model.addAttribute("cartStat", Utils.cartStats(cart));
            model.addAttribute("cartCounter", Utils.countCart((Map<Integer, Cart>) session.getAttribute("cart")));
        }
        else
            model.addAttribute("carts", null);
        
        
        return "cart";
    }
}
