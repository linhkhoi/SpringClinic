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
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MSIGE66
 */
@RestController
@RequestMapping("/api")
public class ApiCartController {
    @Autowired
    private MedicineService medicineService;
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/medicine/{medicineId}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToCart(@PathVariable(name = "medicineId") int medicineId,
            HttpSession session) {
        
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart == null)
            cart = new HashMap<>();
        
        if (cart.containsKey(medicineId) == true) { // sp có trong giỏ
            Cart c = cart.get(medicineId);
            c.setQuantity(c.getQuantity() + 1);
        } else { // sp chưa có trong giỏ
            Medicine p = this.medicineService.getMedicineById(medicineId);
            Cart c = new Cart();
            c.setMedicineId(p.getId());
            c.setMedicineName(p.getName());
            c.setPrice(p.getPrice());
            c.setQuantity(1);
            
            cart.put(medicineId, c);
        }
        
        session.setAttribute("cart", cart);
    }
    
    
    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public void saveOrder(HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        int appId = (int) session.getAttribute("appointmentId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(authentication.getName());
        if (this.prescriptionService.addPrescription(cart, appId, u.getId()) == true){
            session.removeAttribute("cart");
            session.removeAttribute("appointmentId");
        }
            
    }
    
}
//@RestController
//public class ApiCartController {
//    @Autowired
//    private MedicineService medicineService;
//    
//    @GetMapping("/api/medicines")
//    public ResponseEntity<List<Medicine>> listMedicines(){
//        List<Medicine> medicines = this.medicineService.getMedicines("");
//        
//        return new ResponseEntity<>(medicines, HttpStatus.OK);
//    }
//    
//    @GetMapping("/api/cart/{medicineId}")
//    public ResponseEntity<Integer> cart(@PathVariable(value = "medicineId") Integer medicineId,
//                HttpSession session){
//        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
//        if(cart == null)
//            cart = new HashMap<>();
//        
//        if(cart.containsKey(medicineId) == true){
//            Cart c = cart.get(medicineId);
//            c.setQuantity(c.getQuantity()+1);
//        }else{
//            Medicine m = this.medicineService.getMedicineById(medicineId);
//            Cart c = new Cart();
//            c.setMedicineId(m.getId());
//            c.setMedicineName(m.getName());
//            c.setPrice(m.getPrice());
//            c.setQuantity(1);
//            cart.put(medicineId, c);
//        }
//        session.setAttribute("cart", cart);
//        
//        return new ResponseEntity<>(Utils.countCart(cart), HttpStatus.OK);
//    
//    }
//    
//}
