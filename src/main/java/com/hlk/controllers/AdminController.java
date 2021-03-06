/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.User;
import com.hlk.service.AppointmentService;
import com.hlk.service.DoctorService;
import com.hlk.service.MedicalRecordService;
import com.hlk.service.MedicineService;
import com.hlk.service.NurseService;
import com.hlk.service.OrderService;
import com.hlk.service.PatientService;
import com.hlk.service.PrescriptionDetailService;
import com.hlk.service.PrescriptionService;
import com.hlk.service.ScheduleDoctorService;
import com.hlk.service.ScheduleNurseService;
import com.hlk.service.SickService;
import com.hlk.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MSI GE66
 */
@Controller
@ControllerAdvice
public class AdminController {
    @Autowired
    private SickService sickService;
    
    @Autowired
    private MedicineService medicineService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private MedicalRecordService medicalRecordService;
    
    @Autowired
    private NurseService nurseService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private PrescriptionDetailService prescriptionDetailService;
    
    @Autowired
    private ScheduleDoctorService scheduleDoctorService;
    
    @Autowired
    private ScheduleNurseService scheduleNurseService;
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void commomAttr(Model model){
       
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       User u = this.userService.getUserByUsername(authentication.getName());
       model.addAttribute("userInfo", u);
    }
    
    @RequestMapping(value = "/admin/user-admin")
    public String userAdmin(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.userService.countUser(kw));
        model.addAttribute("page", page);
        model.addAttribute("users", this.userService.getUsers(kw,page));
        return "userAdmin";
    }
    
    @RequestMapping(value = "/admin/doctor")
    public String doctor(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.doctorService.countDoctor(kw));
        model.addAttribute("page", page);
        model.addAttribute("doctors", this.doctorService.getDoctors(kw, page));
        return "doctor";
    }
    
    @RequestMapping(value = "/admin/nurse")
    public String nurse(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("nurses", this.nurseService.getNurses(kw));
        return "nurse";
    }
    
    @RequestMapping(value = "/admin/patient")
    public String patient(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.medicineService.countMedicine(kw));
        model.addAttribute("page", page);
        model.addAttribute("patients", this.patientService.getPatients(kw,page));
        return "patient";
    }
    
    @RequestMapping(value = "/admin/sick")
    @Transactional
    public String sicks(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("sicks", this.sickService.getSicks(kw));
        return "sick";
    }
    
    @RequestMapping(value = "/admin/medicine")
    public String medicine(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.medicineService.countMedicine(kw));
        model.addAttribute("page", page);
        model.addAttribute("medicines", this.medicineService.getMedicines(kw, page));
        return "medicine";
    }
    
    @RequestMapping(value = "/admin/appointment")
    public String appointment(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null,toDate = null;
        try{
            String from = params.getOrDefault("fromDate",null);
            if(from != null){
                fromDate = f.parse(from);
            }
            String to = params.getOrDefault("toDate",null);
            if(to != null){
                toDate = f.parse(to);
        }
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        
        
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.appointmentService.countAppointment(kw,fromDate,toDate));
        model.addAttribute("page", page);
        model.addAttribute("appointments", this.appointmentService.getAppointments(kw,fromDate,toDate,page));
        return "appointment";
    }
    
    @RequestMapping(value = "/admin/prescription")
    public String prescription(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("prescriptions", this.prescriptionService.getPrescriptions(kw));
        return "prescription";
    }
    
    @RequestMapping(value = "/admin/prescription-detail")
    public String prescriptionDetail(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("prescriptionDetails", this.prescriptionDetailService.getPrescriptionDetails(kw));
        return "prescriptionDetail";
    }
    
    @RequestMapping(value = "/admin/order")
    public String order(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("orders", this.orderService.getOrders(kw));
        return "order";
    }
    
    @RequestMapping(value = "/admin/schedule-doctor")
    public String scheduleDoctor(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("schedocs", this.scheduleDoctorService.getScheduleDoctors(kw));
        return "scheduleDoctor";
    }
    
    @RequestMapping(value = "/admin/schedule-nurse")
    public String scheduleNurse(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("schenurs", this.scheduleNurseService.getScheduleNurses(kw));
        return "scheduleNurse";
    }
    
    @RequestMapping(value = "/admin/medical-record")
    public String medicalRecord(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        model.addAttribute("meres", this.medicalRecordService.getMedicalRecords(kw));
        return "medicalRecord";
    }
}
