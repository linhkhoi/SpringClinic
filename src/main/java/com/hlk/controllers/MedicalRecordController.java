/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.MedicalRecord;
import com.hlk.service.MedicalRecordService;
import com.hlk.service.PatientService;
import com.hlk.service.SickService;
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
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private SickService sickService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "mereId", defaultValue = "0") int medicalRecordId) {
        if (medicalRecordId > 0) // cập nhật
            model.addAttribute("medicalRecord", this.medicalRecordService.getMedicalRecordById(medicalRecordId));
        else // thêm
            model.addAttribute("medicalRecord", new MedicalRecord());
        model.addAttribute("patientcommom", this.patientService.getPatients(""));
        model.addAttribute("sickcommom", this.sickService.getSicks(""));
        return "medicalRecordEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateMedicalRecord(Model model, @ModelAttribute(value="medicalRecord") @Valid MedicalRecord medicalRecord,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            model.addAttribute("patientcommom", this.patientService.getPatients(""));
            model.addAttribute("sickcommom", this.sickService.getSicks(""));
            return "medicalRecordEdit";
        }
        
        if (!this.medicalRecordService.addOrUpdateMedicalRecord(medicalRecord)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("patientcommom", this.patientService.getPatients(""));
            model.addAttribute("sickcommom", this.sickService.getSicks(""));
            return "medicalRecordEdit";
        }
        
        return "redirect:/admin/medical-record";
    }
    
}
