/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hlk.model.Appointment;
import com.hlk.model.Cart;
import com.hlk.model.GooglePojo;
import com.hlk.model.Patient;
import com.hlk.model.User;
import com.hlk.service.AppointmentService;
import com.hlk.service.PatientService;
import com.hlk.service.UserService;
import com.hlk.utils.Utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hlk.utils.GoogleUtils;
import com.hlk.utils.RestFB;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author MSI GE66
 */
@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private GoogleUtils googleUtils;

    @Autowired
    private RestFB restFB;
    
    @ModelAttribute
    public void commomAttr(Model model, HttpSession session) {
        model.addAttribute("cartStats", Utils.getCartStats((Map<Integer, Cart>) session.getAttribute("cart")));
    }

    @RequestMapping(value = {"/", "/index"})
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/user-admin";
        } else if (request.isUserInRole("ROLE_DOCTOR")) {
            return "redirect:/doctor/history-patient";
        } else if (request.isUserInRole("ROLE_NURSE")) {
            return "redirect:/nurse/add-order";
        } else if (request.isUserInRole("ROLE_PATIENT")) {
            return "index";
        }
        return "index";
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
            @ModelAttribute(value = "user") User user) throws IOException {

        String errMgs = "";
        if (user.getPassword().equals(user.getConfirmPassword())) {
            Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));

            String img = (String) r.get("secure_url");
            System.out.println(img);
            user.setAvatar(img);
            if (this.userDetailsService.addOrUpdateUser(user) == true) {
                User unew = this.userDetailsService.getUserByUsername(user.getUsername());
                
                Patient patient = new Patient();
                patient.setUser(unew);
                if(this.patientService.addOrUpdatePatient(patient)){
                    return "redirect:/login";
                }else{
                    errMgs = "đã có lỗi";
                }
            } else {
                errMgs = "đã có lỗi";
            }
        } else {
            errMgs = " Mật khẩu không khớp";

        }

        model.addAttribute("errMsg", errMgs);
        return "register";

    }

    @RequestMapping(path = "/login")
    public String login() {

        return "login";
    }

    @GetMapping(path = "/book-appointment")
    public String bookAppointment(Model model, @RequestParam(required = false) Map<String, String> params) {
        String msg = params.getOrDefault("msg", "");
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("msg", msg);
        return "bookAppointment";
    }
    
    
    @PostMapping(path = "/book-appointment")
    public String bookingg1(Model model,  @ModelAttribute(value="appointment") @Valid Appointment appointment,BindingResult err) throws ParseException {
        String errMgs = null;
        
        Calendar c1 = Calendar.getInstance();
        c1.setTime(appointment.getMeetDate());
        
        Calendar nineHours = Calendar.getInstance();
        nineHours.setTime(new SimpleDateFormat("HH:mm:ss").parse("09:00:00"));
        nineHours.add(Calendar.DATE, 1);
        
        Calendar eighteenHours = Calendar.getInstance();
        eighteenHours.setTime(new SimpleDateFormat("HH:mm:ss").parse("18:00:00"));
        eighteenHours.add(Calendar.DATE, 1);
        
        Calendar twenoneHours = Calendar.getInstance();
        twenoneHours.setTime(new SimpleDateFormat("HH:mm:ss").parse("21:00:00"));
        twenoneHours.add(Calendar.DATE, 1);
        
        Calendar meeTime = Calendar.getInstance();
        meeTime.setTime(appointment.getMeetTime());
        meeTime.add(Calendar.DATE, 1);
        
        Date checkTime = meeTime.getTime();
        if (checkTime.after(nineHours.getTime()) && checkTime.before(eighteenHours.getTime()) && ((c1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) 
            || (Calendar.DAY_OF_WEEK != Calendar.SUNDAY))) {
            appointment.setExpense(new BigDecimal(100000));
        } else if (checkTime.after(eighteenHours.getTime()) && checkTime.before(twenoneHours.getTime()) && ((c1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) 
            || (Calendar.DAY_OF_WEEK != Calendar.SUNDAY))){
            appointment.setExpense(new BigDecimal(150000));
        } else if (checkTime.after(nineHours.getTime())&& checkTime.before(eighteenHours.getTime()) && ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) 
            || (Calendar.DAY_OF_WEEK == Calendar.SUNDAY))){
            appointment.setExpense(new BigDecimal(150000));
        } else if (checkTime.after(eighteenHours.getTime()) && checkTime.before(twenoneHours.getTime()) && ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) 
            || (Calendar.DAY_OF_WEEK == Calendar.SUNDAY))){
            appointment.setExpense(new BigDecimal(150000));
        } else {
            errMgs = "Giờ không đúng quy định";
            return "redirect:/book-appointment/?msg="+errMgs;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userDetailsService.getUserByUsername(authentication.getName());
        
        appointment.setPatient(this.patientService.getPatientById(u.getId()));
        
        if(this.appointmentService.addOrUpdateAppointment(appointment) == true){
            return "redirect:/";
        } else {
            errMgs = "Đã có lỗi vui lòng thử lại";
        }
//        model.addAttribute("errMsg", errMgs);
        return "redirect:/login";
    }
    
    @RequestMapping("/login-google")
  public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
    String code = request.getParameter("code");
    
    if (code == null || code.isEmpty()) {
      return "redirect:/login?message=google_error";
    }
    String accessToken = googleUtils.getToken(code);
    
    GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
    UserDetails userDetail = googleUtils.buildUser(googlePojo);
    User u = this.userDetailsService.getUserByUsername(userDetail.getUsername());
    if(u == null){
        u = new User();
        u.setUsername(userDetail.getUsername());
        u.setPassword(userDetail.getPassword());
        u.setFirstName(googlePojo.getName());
        u.setEmail(googlePojo.getEmail());
        u.setDateJoined(new Date());
        u.setIsActive(true);
        u.setUserRole("ROLE_PATIENT");
        this.userDetailsService.addOrUpdateUser(u);
    }
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
        userDetail.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return "redirect:/";
  }
  
  @RequestMapping("/login-facebook")
  public String loginFacebook(HttpServletRequest request) {
    String code = request.getParameter("code");
    String accessToken = "";
    try {
      accessToken = restFB.getToken(code);
    } catch (IOException e) {
      return "login?facebook=error";
    }
    com.restfb.types.User user = restFB.getUserInfo(accessToken);
    UserDetails userDetail = restFB.buildUser(user);
    User u = this.userDetailsService.getUserByUsername(userDetail.getUsername());
    if(u == null){
         u = new User();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setUsername(userDetail.getUsername());
        u.setPassword(userDetail.getPassword());
        u.setDateJoined(new Date());
        u.setIsActive(true);
        u.setUserRole("ROLE_PATIENT");
        this.userDetailsService.addOrUpdateUser(u);
    }
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
        userDetail.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return "redirect:/";
  }

}
