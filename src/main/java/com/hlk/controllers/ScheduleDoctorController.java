/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.ScheduleDoctor;
import com.hlk.service.ScheduleDoctorService;
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
@RequestMapping("/admin/schedoc-edit")
public class ScheduleDoctorController {
    @Autowired
    private ScheduleDoctorService scheduleDoctorService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "scheduleDoctorId", defaultValue = "0") int scheduleDoctorId) {
        if (scheduleDoctorId > 0) // cập nhật
            model.addAttribute("scheduleDoctor", this.scheduleDoctorService.getScheduleDoctorById(scheduleDoctorId));
        else // thêm
            model.addAttribute("scheduleDoctor", new ScheduleDoctor());
        return "scheduleDoctorEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateScheduleDoctor(Model model, @ModelAttribute(value="scheduleDoctor") @Valid ScheduleDoctor scheduleDoctor,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "scheduleDoctorEdit";
        }
        
        if (!this.scheduleDoctorService.addOrUpdateScheduleDoctor(scheduleDoctor)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "scheduleDoctorEdit";
        }
        
        return "redirect:/admin/schedule-doctor";
    }
    
}
