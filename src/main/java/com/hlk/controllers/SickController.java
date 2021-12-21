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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author MSIGE66
 */
@Controller
public class SickController {
    @Autowired
    private SickService sickService;
    
    @RequestMapping(value = "/admin/sick-edit", method = RequestMethod.GET)
    public String addOrUpdateView(Model model,
            @RequestParam(name = "sickId", defaultValue = "0") int sickId) {
        if (sickId > 0) // cập nhật
            model.addAttribute("sick", this.sickService.getSickById(sickId));
        else // thêm
            model.addAttribute("sick", new Sick());
        
        
        return "sickEdit";
    }
    
    @RequestMapping(value = "/admin/sick-edit", method = RequestMethod.POST)
    public String addOrUpdateSick(Model model, @ModelAttribute(value="sick") @Valid Sick sick,BindingResult err) throws UnsupportedEncodingException {
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
