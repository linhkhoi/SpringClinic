/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.MedicalRecord;
import com.hlk.service.MedicalRecordService;
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
@RequestMapping("/admin/mere-edit")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "mereId", defaultValue = "0") int medicalRecordId) {
        if (medicalRecordId > 0) // cập nhật
            model.addAttribute("medicalRecord", this.medicalRecordService.getMedicalRecordById(medicalRecordId));
        else // thêm
            model.addAttribute("medicalRecord", new MedicalRecord());
        return "medicalRecordEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateMedicalRecord(Model model, @ModelAttribute(value="medicalRecord") @Valid MedicalRecord medicalRecord,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "medicalRecordEdit";
        }
        
        if (!this.medicalRecordService.addOrUpdateMedicalRecord(medicalRecord)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "medicalRecordEdit";
        }
        
        return "redirect:/admin/medical-record";
    }
    
}
