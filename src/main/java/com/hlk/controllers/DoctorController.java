/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Cart;
import com.hlk.model.Doctor;
import com.hlk.model.MedicalRecord;
import com.hlk.model.Patient;
import com.hlk.model.User;
import com.hlk.service.AppointmentService;
import com.hlk.service.DoctorService;
import com.hlk.service.MedicalRecordService;
import com.hlk.service.MedicineService;
import com.hlk.service.PatientService;
import com.hlk.service.ScheduleDoctorService;
import com.hlk.service.SickService;
import com.hlk.service.UserService;
import com.hlk.utils.Utils;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MSIGE66
 */
@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private SickService sickService;
    @Autowired
    private ScheduleDoctorService scheduleDoctorService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/admin/doctor-edit", method = RequestMethod.GET)
    public String addOrUpdateView(Model model,
            @RequestParam(name = "doctorId", defaultValue = "0") int doctorId) {
        if (doctorId > 0) // cập nhật
            model.addAttribute("doctor", this.doctorService.getDoctorById(doctorId));
        else // thêm
            model.addAttribute("doctor", new Doctor());
        model.addAttribute("usercommom", this.userService.getUsers(""));
        return "doctorEdit";
    }
    
    @RequestMapping(value = "/admin/doctor-edit", method = RequestMethod.POST)
    public String addOrUpdateDoctor(Model model, @ModelAttribute(value="doctor") @Valid Doctor doctor,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            model.addAttribute("usercommom", this.userService.getUsers(""));
            return "doctorEdit";
        }
        
        if (!this.doctorService.addOrUpdateDoctor(doctor)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("usercommom", this.userService.getUsers(""));
            return "doctorEdit";
        }
        
        return "redirect:/admin/doctor";
    }
    
    @RequestMapping(value = "/doctor/history-patient", method = RequestMethod.GET)
    public String historyPatient(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.patientService.countPatient(kw));
        model.addAttribute("page", page);
        model.addAttribute("patientcommom", this.patientService.getPatients(kw,page));
        return "historyPatient";
    }
    
    @RequestMapping(value = "/doctor/medical-patient/{id}/", method = RequestMethod.GET)
    public String medicalPatient(Model model, @PathVariable int id) {
         model.addAttribute("medicalPatients", this.medicalRecordService.getMedicalRecordsByPatient(id));
        return "patientDetail";
    }
    
    
    @RequestMapping(value = "/doctor/history-patient-detail/{id}/", method = RequestMethod.GET)
    public String historyDetailPatient(Model model,@PathVariable int id,@RequestParam(required = false) Map<String, String> params) {
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
        
        
        int page = Integer.parseInt(params.getOrDefault("page","1"));
        model.addAttribute("count", this.appointmentService.countAppointmentByPatient(id,fromDate,toDate));
        model.addAttribute("page", page);
        model.addAttribute("historyPatients", this.appointmentService.getAppointmentByPatient(id, fromDate, toDate, page));
        return "patientHistoryDetail";
    }
    
    
    @RequestMapping(value = "/doctor/add-prescription", method = RequestMethod.GET)
    public String addPrescription(Model model,@RequestParam(required = false) Map<String, String> params, HttpSession session) {
        String kw = params.getOrDefault("kw","");
        String appointmentId = params.getOrDefault("appointmentId","");
        session.setAttribute("appointmentId", Integer.parseInt(appointmentId));
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.medicineService.countMedicine(kw));
        model.addAttribute("page", page);
        model.addAttribute("medicines", this.medicineService.getMedicines(kw, page));
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, Cart>) session.getAttribute("cart")));
        return "medicine";
    }
    
    @RequestMapping(value = "/doctor/add-medical", method = RequestMethod.GET)
    public String addOrUpdateMedical(Model model,@RequestParam(required = false) Map<String, String> params) {
        String medicalRecordId = params.getOrDefault("medicalRecordId", "-1");
        String patientId = params.getOrDefault("patientId", "0");
        Patient p = this.patientService.getPatientById(Integer.parseInt(patientId));
        model.addAttribute("patient", p);
        MedicalRecord mr = new MedicalRecord();
        mr.setPatient(p);
        if (Integer.parseInt(medicalRecordId) > 0) // cập nhật
            model.addAttribute("medicalRecord", this.medicalRecordService.getMedicalRecordById(Integer.parseInt(medicalRecordId)));
        else // thêm
            model.addAttribute("medicalRecord", mr);
         model.addAttribute("sickcommom", this.sickService.getSicks(""));
        return "addMedical";
    }
    
    @RequestMapping(value = "/doctor/add-medical", method = RequestMethod.POST)
    public String addOrUpdateMedicalR(Model model, @ModelAttribute(value="medicalRecord") @Valid MedicalRecord medicalRecord,BindingResult err) throws UnsupportedEncodingException {
        
        
        if (!this.medicalRecordService.addOrUpdateMedicalRecord(medicalRecord)) {
            System.out.println(medicalRecord.getEndDate());
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
             model.addAttribute("sickcommom", this.sickService.getSicks(""));
            return "addMedical";
        }
        
        return "redirect:/doctor/history-patient";
    }
    
    @RequestMapping(value = "/doctor/schedule-doctor/")
    public String scheduleDoctor(Model model, @RequestParam(required = false) Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userService.getUserByUsername(authentication.getName());
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
        int page = Integer.parseInt(params.getOrDefault("page","1"));
        
        
        model.addAttribute("count", this.scheduleDoctorService.countSchedule(u.getId(), fromDate, toDate));
        model.addAttribute("page", page);
        model.addAttribute("schedocs", this.scheduleDoctorService.getScheduleDoctorByDoctor(u.getId(),fromDate,toDate,page));
        return "scheduleDoctor";
    }
    
    @RequestMapping(value = "/doctor/list-appointment/{id}/", method = RequestMethod.GET)
    public String listAppointmetPatient(Model model,@PathVariable int id,@RequestParam(required = false) Map<String, String> params) {
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
        model.addAttribute("count", this.appointmentService.countAppointmentForPatient(id,fromDate,toDate));
        model.addAttribute("page", page);
        model.addAttribute("historyPatients", this.appointmentService.getAppointmentForPatient(id,fromDate,toDate,page));
        return "appointmentPatient";
    }
}
