/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Cart;
import com.hlk.model.Medicine;
import com.hlk.model.User;
import com.hlk.service.MedicineService;
import com.hlk.service.PrescriptionService;
import com.hlk.service.UserService;
import com.hlk.utils.Utils;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MSIGE66
 */
//@RestController
//@RequestMapping("/api")
//public class ApiCartController {
//    @Autowired
//    private MedicineService medicineService;
//    
//    @Autowired
//    private PrescriptionService prescriptionService;
//    
//    @Autowired
//    private UserService userService;
//    
//    
//    @GetMapping("/medicine/{medicineId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void addProductToCart(@PathVariable(name = "medicineId") int medicineId,
//            HttpSession session) {
//        
//        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
//        if (cart == null)
//            cart = new HashMap<>();
//        
//        if (cart.containsKey(medicineId) == true) { // sp có trong giỏ
//            Cart c = cart.get(medicineId);
//            c.setQuantity(c.getQuantity() + 1);
//        } else { // sp chưa có trong giỏ
//            Medicine p = this.medicineService.getMedicineById(medicineId);
//            Cart c = new Cart();
//            c.setMedicineId(p.getId());
//            c.setMedicineName(p.getName());
//            c.setPrice(p.getPrice());
//            c.setQuantity(1);
//            
//            cart.put(medicineId, c);
//        }
//        
//        session.setAttribute("cart", cart);
//    }
//    
//    
//    @PostMapping("/pay")
//    @ResponseStatus(HttpStatus.OK)
//    public void saveOrder(HttpSession session) {
//        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
//        int appId = (int) session.getAttribute("appointmentId");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User u = this.userService.getUserByUsername(authentication.getName());
//        if (this.prescriptionService.addPrescription(cart, appId, u.getId()) == true){
//            session.removeAttribute("cart");
//            session.removeAttribute("appointmentId");
//        }
//            
//    }
//    
//}
@RestController
public class ApiCartController {
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("api/cart")
    public int addToCart(@RequestBody Cart params, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart==null)
            cart = new HashMap<>();
        
        int medicineId = params.getMedicineId();
        if(cart.containsKey(medicineId)==true){
            Cart c = cart.get(medicineId);
            c.setQuantity(c.getQuantity()+1);
        } else {
            cart.put(medicineId, params);
        }
        
        session.setAttribute("cart", cart);
        
        return Utils.countCart(cart);
    }
    
    @PutMapping("api/cart")
    public ResponseEntity<Map<String, BigDecimal>> updateCart(@RequestBody Cart params, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart==null)
            cart = new HashMap<>();
        
        int medicineId = params.getMedicineId();
        if(cart.containsKey(medicineId)==true){
            Cart c = cart.get(medicineId);
            c.setQuantity(params.getQuantity());
        }
        
        session.setAttribute("cart", cart);
        return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK);

    }
    
    @DeleteMapping("api/cart/{medicineId}")
    public ResponseEntity<Map<String, BigDecimal>> deleteCartItem(@PathVariable(value = "medicineId") int medicineId, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if(cart != null & cart.containsKey(medicineId)){
            cart.remove(medicineId);
            
            session.setAttribute("cart", cart);
        }
        
        return new ResponseEntity<>(Utils.cartStats(cart), HttpStatus.OK);

    }
    
    @PostMapping("api/pay")
    public HttpStatus saveOrder(HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        int appId = (int) session.getAttribute("appointmentId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(authentication.getName());
        if (this.prescriptionService.addPrescription(cart, appId, u.getId()) == true){
            session.removeAttribute("cart");
            session.removeAttribute("appointmentId");
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
