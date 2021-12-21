/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hlk.model.Medicine;
import com.hlk.service.MedicineService;
import com.hlk.service.SickService;
import com.hlk.validator.WebAppValidator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MSIGE66
 */
@Controller
@RequestMapping("/admin/medicine-edit")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    
    @Autowired
    private SickService sickService;
    
    @Autowired
    private WebAppValidator medicineValidator;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(medicineValidator);
    }
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "medicineId", defaultValue = "0") int medicineId) {
        if (medicineId > 0) // cập nhật
            model.addAttribute("medicine", this.medicineService.getMedicineById(medicineId));
        else // thêm
            model.addAttribute("medicine", new Medicine());
        
        model.addAttribute("sickcommom", this.sickService.getSicks(""));
        return "medicineEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateMedicine(Model model, @ModelAttribute(value="medicine") @Valid Medicine medicine,BindingResult err) throws UnsupportedEncodingException, IOException {
        if (err.hasErrors()) {
            model.addAttribute("sickcommom", this.sickService.getSicks(""));
            return "medicineEdit";
        }
        
        Map r = this.cloudinary.uploader().upload(medicine.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        
        String img = (String) r.get("secure_url");
        medicine.setImage(img);
        
        if (!this.medicineService.addOrUpdateMedicine(medicine)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("sickcommom", this.sickService.getSicks(""));
            return "medicineEdit";
        }
        
        return "redirect:/admin/medicine";
    }
}
