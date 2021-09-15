/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Patient;
import com.hlk.service.PatientService;
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
@RequestMapping("/admin/patient-edit")
public class PatientController {
    @Autowired
    private PatientService patientService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "patientId", defaultValue = "0") int patientId) {
        if (patientId > 0) // cập nhật
            model.addAttribute("patient", this.patientService.getPatientById(patientId));
        else // thêm
            model.addAttribute("patient", new Patient());
        return "patientEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdatePatient(Model model, @ModelAttribute(value="patient") @Valid Patient patient,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "patientEdit";
        }
        
        if (!this.patientService.addOrUpdatePatient(patient)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "patientEdit";
        }
        
        return "redirect:/admin/patient";
    }
    
}
