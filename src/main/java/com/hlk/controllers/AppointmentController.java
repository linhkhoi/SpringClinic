/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Appointment;
import com.hlk.service.AppointmentService;
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

@Controller
@RequestMapping("/admin/appointment-edit")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "appointmentId", defaultValue = "0") int appointmentId) {
        if (appointmentId > 0) // cập nhật
            model.addAttribute("appointment", this.appointmentService.getAppointmentById(appointmentId));
        else // thêm
            model.addAttribute("appointment", new Appointment());
        return "appointmentEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateAppointment(Model model, @ModelAttribute(value="appointment") @Valid Appointment appointment,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "appointmentEdit";
        }
        
        if (!this.appointmentService.addOrUpdateAppointment(appointment)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "appointmentEdit";
        }
        
        return "redirect:/admin/appointment";
    }
}