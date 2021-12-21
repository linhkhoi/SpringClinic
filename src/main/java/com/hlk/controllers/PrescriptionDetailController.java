/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.PrescriptionDetail;
import com.hlk.service.MedicineService;
import com.hlk.service.PrescriptionDetailService;
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
@RequestMapping("/admin/prede-edit")
public class PrescriptionDetailController {
    @Autowired
    private PrescriptionDetailService prescriptionDetailService;
    
    @Autowired
    private MedicineService medicineService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "predeId", defaultValue = "0") int prescriptionDetailId) {
        if (prescriptionDetailId > 0) // cập nhật
            model.addAttribute("prescriptionDetail", this.prescriptionDetailService.getPrescriptionDetailById(prescriptionDetailId));
        else // thêm
            model.addAttribute("prescriptionDetail", new PrescriptionDetail());
        model.addAttribute("medicinecommom", this.medicineService.getMedicines(""));
        return "prescriptionDetailEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdatePrescriptionDetail(Model model, @ModelAttribute(value="prescriptionDetail") @Valid PrescriptionDetail prescriptionDetail,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            model.addAttribute("medicinecommom", this.medicineService.getMedicines(""));
            return "prescriptionDetailEdit";
        }
        
        if (!this.prescriptionDetailService.addOrUpdatePrescriptionDetail(prescriptionDetail)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("medicinecommom", this.medicineService.getMedicines(""));
            return "prescriptionDetailEdit";
        }
        
        return "redirect:/admin/prescriptionDetail";
    }
    
}
