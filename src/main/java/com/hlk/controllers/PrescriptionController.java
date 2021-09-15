/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Prescription;
import com.hlk.service.PrescriptionService;
import java.io.UnsupportedEncodingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MSIGE66
 */
@Controller
@RequestMapping("/admin/prescription-edit")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "prescriptionId", defaultValue = "0") int prescriptionId) {
        if (prescriptionId > 0) // cập nhật
            model.addAttribute("prescription", this.prescriptionService.getPrescriptionById(prescriptionId));
        else // thêm
            model.addAttribute("prescription", new Prescription());
        return "prescriptionEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdatePrescription(Model model, @ModelAttribute(value="prescription") @Valid Prescription prescription,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "prescriptionEdit";
        }
        
        if (!this.prescriptionService.addOrUpdatePrescription(prescription)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "prescriptionEdit";
        }
        
        return "redirect:/admin/prescription";
    }
    
}
