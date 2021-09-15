/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.service.AppointmentService;
import com.hlk.service.OrderService;
import com.hlk.service.PrescriptionService;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CanvasjsChartController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin/chart-count-patient", method = RequestMethod.GET)
    public String chartCountPatientByYear(ModelMap modelMap, @RequestParam(required = false) Map<String, String> params) {
        String year = params.getOrDefault("year","2021");
        modelMap.addAttribute("year", year);
        modelMap.addAttribute("dataPointsList", this.appointmentService.getCountPatientByYear(Integer.parseInt(year)));
        modelMap.addAttribute("statistic", this.appointmentService.getStatisticPatientByYear(Integer.parseInt(year)));
        
        return "chartCountPatientByYear";
    }
    @RequestMapping(value = "/admin/chart-sales-month", method = RequestMethod.GET)
    public String chartSalesByMonth(ModelMap modelMap, @RequestParam(required = false) Map<String, String> params) {
        String year = params.getOrDefault("year","2021");
        String month = params.getOrDefault("month","1");
        modelMap.addAttribute("year", year);
        modelMap.addAttribute("month", month);
        modelMap.addAttribute("dataPointsList", this.orderService.getCountSalesByMonth(Integer.parseInt(month), Integer.parseInt(year)));
        modelMap.addAttribute("statistic", this.orderService.getStatisticSalesByMonth(Integer.parseInt(month), Integer.parseInt(year)));
        
        return "chartSalesOfMonth";
    }
    @RequestMapping(value = "/admin/chart-sales-quarter", method = RequestMethod.GET)
    public String chartSalesByQuarter(ModelMap modelMap, @RequestParam(required = false) Map<String, String> params) {
        String year = params.getOrDefault("year","2021");
        String quarter = params.getOrDefault("quarter","1");
        modelMap.addAttribute("year", year);
        modelMap.addAttribute("quarter", quarter);
        modelMap.addAttribute("dataPointsList", this.orderService.getCountSalesByQuarter(Integer.parseInt(quarter), Integer.parseInt(year)));
        modelMap.addAttribute("statistic", this.orderService.getStatisticSalesByQuarter(Integer.parseInt(quarter), Integer.parseInt(year)));
        return "chartSalesOfQuarter";
    }
    @RequestMapping(value = "/admin/chart-sales-year", method = RequestMethod.GET)
    public String chartSalesByYear(ModelMap modelMap, @RequestParam(required = false) Map<String, String> params) {
        String year = params.getOrDefault("year","2021");
        modelMap.addAttribute("year", year);
        modelMap.addAttribute("dataPointsList", this.orderService.getCountSalesByYear(Integer.parseInt(year)));
        modelMap.addAttribute("statistic", this.orderService.getStatisticSalesByYear(Integer.parseInt(year)));
        
        return "chartSalesOfYear";
    }

}
