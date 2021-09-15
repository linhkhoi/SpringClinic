/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Sick;
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
@RequestMapping("/admin/sick-edit")
public class SickController {
    @Autowired
    private SickService sickService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "sickId", defaultValue = "0") int sickId) {
        if (sickId > 0) // cập nhật
            model.addAttribute("sick", this.sickService.getSickById(sickId));
        else // thêm
            model.addAttribute("sick", new Sick());
        
        
        return "sickEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateProduct(Model model, @ModelAttribute(value="sick") @Valid Sick sick,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "sickEdit";
        }
        
        if (!this.sickService.addOrUpdateSick(sick)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "sickEdit";
        }
        
        return "redirect:/admin/sick";
    }
}
