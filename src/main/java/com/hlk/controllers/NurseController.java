/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.hlk.model.Appointment;
import com.hlk.model.Nurse;
import com.hlk.model.Order;
import com.hlk.model.User;
import com.hlk.service.AppointmentService;
import com.hlk.service.MedicineService;
import com.hlk.service.NurseService;
import com.hlk.service.OrderService;
import com.hlk.service.PatientService;
import com.hlk.service.PrescriptionService;
import com.hlk.service.ScheduleNurseService;
import com.hlk.service.UserService;
import com.hlk.utils.CreateZaloOrder;
import com.hlk.utils.GetZaloOrderStatus;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private ScheduleNurseService scheduleNurseService;
    
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/nurse-edit", method = RequestMethod.GET)
    public String addOrUpdateView(Model model,
            @RequestParam(name = "nurseId", defaultValue = "0") int nurseId) {
        if (nurseId > 0) // cập nhật
        {
            model.addAttribute("nurse", this.nurseService.getNurseById(nurseId));
        } else // thêm
        {
            model.addAttribute("nurse", new Nurse());
        }
        model.addAttribute("usercommom", this.userService.getUsers(""));
        return "nurseEdit";
    }

    @RequestMapping(value = "/admin/nurse-edit", method = RequestMethod.POST)
    public String addOrUpdateNurse(Model model, @ModelAttribute(value = "nurse") @Valid Nurse nurse, BindingResult err) throws UnsupportedEncodingException {
        if (err.hasErrors()) {
            model.addAttribute("usercommom", this.userService.getUsers(""));
            return "nurseEdit";
        }

        if (!this.nurseService.addOrUpdateNurse(nurse)) {
            model.addAttribute("errMsg", "Hệ thóng đang có lỗi! Vui lòng quay lại sau!");
            model.addAttribute("usercommom", this.userService.getUsers(""));
            return "nurseEdit";
        }

        return "redirect:/admin/nurse";
    }

    @RequestMapping(value = "/nurse/add-order", method = RequestMethod.GET)
    public String addOrder(Model model, @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw","");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        try{
            String from = params.getOrDefault("createdDate",null);
            if(from != null){
                fromDate = f.parse(from);
            }
            
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        
        
        int page = Integer.parseInt(params.getOrDefault("page","1")) ;
        model.addAttribute("count", this.nurseService.CountPrescriptionForOrder(kw, fromDate));
        model.addAttribute("page", page);
        
        model.addAttribute("prescriptions", this.nurseService.getPrescriptionForOrder(kw,fromDate,page));
        return "prescription";
    }

    @RequestMapping(value = "/nurse/check-order", method = RequestMethod.GET)
    public String checkOrder(Model model, @RequestParam(required = false) Map<String, String> params) {
        String id = params.getOrDefault("prescriptionId", "");
        model.addAttribute("data", this.nurseService.getDetailOrder(Integer.parseInt(id)));
        model.addAttribute("preDetail", this.medicineService.getMedicinesByPrescription(Integer.parseInt(id)));
        return "checkOrder";
    }

    @RequestMapping(value = "/nurse/pay-by-zalo", method = RequestMethod.GET)
    public void payByZalo(HttpServletResponse httpServletResponse, @RequestParam(required = false) Map<String, String> params) throws IOException {
        String id = params.getOrDefault("presciptionId", "");
        String total = params.getOrDefault("total", "");
        CreateZaloOrder a = new CreateZaloOrder();
        httpServletResponse.setHeader("Location", a.urlToPay(new BigDecimal(total), Integer.parseInt(id)));
        httpServletResponse.setStatus(302);
    }

    @RequestMapping(value = "/nurse/check-zalo/")
    public String checkpay(Model model, @RequestParam(required = false) Map<String, String> params) throws IOException, URISyntaxException {
        String appTransId = params.getOrDefault("appId", "");
        String preId = params.getOrDefault("preId", "");
        if (GetZaloOrderStatus.getStatus(appTransId) == 1) {
            return "forward:/nurse/create-order/?prescriptionId=" + preId;

        }
        return "no";
    }

    @RequestMapping(value = "/nurse/create-order/")
    public String createOrderToDB(@RequestParam(required = false) Map<String, String> params) throws IOException, URISyntaxException {
        String preId = params.getOrDefault("prescriptionId", "");
        Order order = new Order();
        Date date = new Date();
        order.setCreatedDate(date);
        order.setTotalPriceOrder(new BigDecimal((long) this.nurseService.getDetailOrder(Integer.parseInt(preId)).get("total")));
        order.setPrescription(this.prescriptionService.getPrescriptionById(Integer.parseInt(preId)));
        order.setNurse(this.nurseService.getNurseById(3));

        if (!this.orderService.addOrUpdateOrder(order)) {

            return "redirect:/nurse/check-order?prescriptionId="+preId;
        }
        if (!this.prescriptionService.updateIsPaid(Integer.parseInt(preId))) {
            return "redirect:/nurse/check-order?prescriptionId="+preId;
        }
        return "redirect:/nurse/add-order";
    }

    @RequestMapping("/nurse/sent-email")
    public String sendSimpleEmail(@RequestParam(required = false) Map<String, String> params) {
         String id = params.getOrDefault("id", "");
         
         Appointment app = this.appointmentService.getAppointmentById(Integer.parseInt(id));
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
         String strDate = dateFormat.format(app.getMeetDate());  
         String content = "Clinic was confirm your appointment! See you on " + strDate;
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(this.patientService.getEmailPatientByAppointmentId(Integer.parseInt(id)));
        message.setSubject("Confirm Appointment");
        message.setText(content);
 
        try{
            this.emailSender.send(message);
        }catch(MailException e){
            System.err.println(e);
        }
        
 
        return "redirect:/nurse/confirm-appointment";
    }
    
    
    @RequestMapping(value = "/nurse/confirm-appointment", method = RequestMethod.GET)
    public String confirmAppointment(Model model, @RequestParam(required = false) Map<String, String> params) {
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
        model.addAttribute("count", this.nurseService.countAppointmentForConfirm(kw, fromDate, toDate, false));
        model.addAttribute("page", page);
        model.addAttribute("appointments", this.nurseService.getAppointmentForConfirm(kw,fromDate,toDate,page, false));
        return "appointment";
    }
    
    @RequestMapping(value = "/nurse/see-history-appointment", method = RequestMethod.GET)
    public String seeAppointment(Model model, @RequestParam(required = false) Map<String, String> params) {
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
        model.addAttribute("appointments", this.nurseService.getAppointmentForConfirm(kw,fromDate,toDate,page, true));
        return "appointment";
    }
    
    @RequestMapping(value = "/nurse/list-appointment/")
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
    
    @RequestMapping(value = "/nurse/checked-appointment", method = RequestMethod.GET)
    public String checkedAppointment(Model model, @RequestParam(required = false) Map<String, String> params) {
        String appointmentId = params.getOrDefault("appointmentId", "");
//        String nurseId = params.getOrDefault("nurseId", "");
        Nurse nurse = this.nurseService.getNurseById(3);
        Appointment app = this.appointmentService.getAppointmentById(Integer.parseInt(appointmentId));
        app.setNurse(nurse);
        
        if (!this.appointmentService.addOrUpdateAppointment(app)) {
            return "redirect:/index";
        }
        return "redirect:/nurse/sent-email/?id="+appointmentId;
        
    }
    
    @RequestMapping(value = "/nurse/see-order-list/", method = RequestMethod.GET)
    public String seeOrderList(Model model, @RequestParam(required = false) Map<String, String> params) {
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
        
        
        int page = Integer.parseInt(params.getOrDefault("page","1"));
        
        model.addAttribute("count", this.orderService.countOrder(kw, fromDate, toDate));
        model.addAttribute("page", page);
        
        model.addAttribute("orders", this.orderService.getOrders(kw,fromDate,toDate,page));
        return "order";
    }
    
    @RequestMapping(value = "/nurse/schedule-nurse")
    public String scheduleNurses(Model model, @RequestParam(required = false) Map<String, String> params) {
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
        
        
        model.addAttribute("count", this.scheduleNurseService.countSchedule(u.getId(), fromDate, toDate));
        model.addAttribute("page", page);
       model.addAttribute("schenurs", this.scheduleNurseService.getScheduleNurseByNurse(u.getId(), fromDate, toDate,page));
       return "scheduleNurse";
    }
    
    @RequestMapping(value = "/nurse/see-order/", method = RequestMethod.GET)
    public String seeOrderDetail(Model model, @RequestParam(required = false) Map<String, String> params) {
        String id = params.getOrDefault("preId", "");
        String orderId = params.getOrDefault("orderId", "");
        model.addAttribute("orderDetail", this.orderService.getOrderById(Integer.parseInt(orderId)));
        model.addAttribute("data", this.nurseService.getDetailOrder(Integer.parseInt(id)));
        model.addAttribute("preDetail", this.medicineService.getMedicinesByPrescription(Integer.parseInt(id)));
        return "seeOrder";
    }
}
