/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.ScheduleNurse;
import com.hlk.service.NurseService;
import com.hlk.service.ScheduleNurseService;
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
@RequestMapping("/admin/schenur-edit")
public class ScheduleNurseController {
    @Autowired
    private ScheduleNurseService scheduleNurseService;
     @Autowired
    private NurseService nurseService;
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "scheduleNurseId", defaultValue = "0") int scheduleNurseId) {
        if (scheduleNurseId > 0) // cập nhật
            model.addAttribute("scheduleNurse", this.scheduleNurseService.getScheduleNurseById(scheduleNurseId));
        else // thêm
            model.addAttribute("scheduleNurse", new ScheduleNurse());
        
        model.addAttribute("nursecommom", this.nurseService.getNurses(""));
        return "scheduleNurseEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateScheduleNurse(Model model, @ModelAttribute(value="scheduleNurse") @Valid ScheduleNurse scheduleNurse,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            model.addAttribute("nursecommom", this.nurseService.getNurses(""));
            return "scheduleNurseEdit";
        }
        
        if (!this.scheduleNurseService.addOrUpdateScheduleNurse(scheduleNurse)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("nursecommom", this.nurseService.getNurses(""));
            return "scheduleNurseEdit";
        }
        
        return "redirect:/admin/schedule-nurse";
    }
    
}
