/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Order;
import com.hlk.service.NurseService;
import com.hlk.service.OrderService;
import com.hlk.service.PrescriptionService;
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
@RequestMapping("/admin/order-edit")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private NurseService nurseService;
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping(value = "/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "orderId", defaultValue = "0") int orderId) {
        if (orderId > 0) // cập nhật
        {
            model.addAttribute("order", this.orderService.getOrderById(orderId));
        } else // thêm
        {
            model.addAttribute("order", new Order());
        }
        model.addAttribute("nursecommom", this.nurseService.getNurses(""));
        model.addAttribute("prescriptioncommom", this.prescriptionService.getPrescriptions(""));

        return "orderEdit";
    }

    @PostMapping(value = "/")
    public String addOrUpdateOrder(Model model, @ModelAttribute(value = "order") @Valid Order order, BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            model.addAttribute("nursecommom", this.nurseService.getNurses(""));
            model.addAttribute("prescriptioncommom", this.prescriptionService.getPrescriptions(""));
            return "orderEdit";
        }

        if (!this.orderService.addOrUpdateOrder(order)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("nursecommom", this.nurseService.getNurses(""));
            return "orderEdit";
        }

        return "redirect:/admin/order";
    }

}
