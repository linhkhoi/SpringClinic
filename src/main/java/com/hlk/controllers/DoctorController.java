/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Doctor;
import com.hlk.model.MedicalRecord;
import com.hlk.model.Patient;
import com.hlk.service.AppointmentService;
import com.hlk.service.DoctorService;
import com.hlk.service.MedicalRecordService;
import com.hlk.service.MedicineService;
import com.hlk.service.PatientService;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @RequestMapping(value = "/admin/doctor-edit", method = RequestMethod.GET)
    public String addOrUpdateView(Model model,
            @RequestParam(name = "doctorId", defaultValue = "0") int doctorId) {
        if (doctorId > 0) // cập nhật
            model.addAttribute("doctor", this.doctorService.getDoctorById(doctorId));
        else // thêm
            model.addAttribute("doctor", new Doctor());
        return "doctorEdit";
    }
    
    @RequestMapping(value = "/admin/doctor-edit", method = RequestMethod.POST)
    public String addOrUpdateDoctor(Model model, @ModelAttribute(value="doctor") @Valid Doctor doctor,BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            return "doctorEdit";
        }
        
        if (!this.doctorService.addOrUpdateDoctor(doctor)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "doctorEdit";
        }
        
        return "redirect:/admin/doctor";
    }
    
    @RequestMapping(value = "/doctor/history-patient", method = RequestMethod.GET)
    public String historyPatient() {
        
        return "historyPatient";
    }
    
    @RequestMapping(value = "/doctor/medical-patient", method = RequestMethod.GET)
    public String medicalPatient(Model model, @RequestParam(name = "patientId") int patientId) {
        model.addAttribute("patientId", patientId);
         model.addAttribute("medicalPatients", this.doctorService.getPatientListById(patientId));
        return "patientDetail";
    }
    
    
    @RequestMapping(value = "/doctor/history-patient-detail", method = RequestMethod.GET)
    public String historyDetailPatient(Model model, @RequestParam(name = "patientId") int patientId) {
         model.addAttribute("historyPatients", this.appointmentService.getAppointmentByPatient(patientId));
        return "patientHistoryDetail";
    }
    
    
    @RequestMapping(value = "/doctor/add-prescription", method = RequestMethod.GET)
    public String addPrescription(Model model,@RequestParam(required = false) Map<String, String> params, HttpSession session) {
        String kw = params.getOrDefault("kw","");
        String appointmentId = params.getOrDefault("appointmentId","");
        session.setAttribute("appointmentId", Integer.parseInt(appointmentId));
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.medicineService.countMedicine());
        model.addAttribute("page", page);
        model.addAttribute("medicines", this.medicineService.getMedicines(kw, page));
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
        return "addMedical";
    }
    
    @RequestMapping(value = "/doctor/add-medical", method = RequestMethod.POST)
    public String addOrUpdateMedicalR(Model model, @ModelAttribute(value="medicalRecord") @Valid MedicalRecord medicalRecord,BindingResult err) throws UnsupportedEncodingException {
        
        
        if (!this.medicalRecordService.addOrUpdateMedicalRecord(medicalRecord)) {
            System.out.println(medicalRecord.getEndDate());
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            return "addMedical";
        }
        
        return "redirect:/doctor/history-patient";
    }
}
