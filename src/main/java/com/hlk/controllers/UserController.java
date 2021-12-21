/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hlk.model.User;
import com.hlk.service.UserService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
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
@RequestMapping("/admin/user-edit")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private Cloudinary cloudinary;
    @GetMapping(value ="/")
    public String addOrUpdateView(Model model,
            @RequestParam(name = "userId", defaultValue = "0") int userId) {
        if (userId > 0) // cập nhật
            model.addAttribute("user", this.userService.getUserById(userId));
        else // thêm
            model.addAttribute("user", new User());
        
        
        return "userEdit";
    }
    
    @PostMapping(value ="/")
    public String addOrUpdateProduct(Model model, @ModelAttribute(value="user") @Valid User user,BindingResult err) throws UnsupportedEncodingException, IOException {
        if (err.hasErrors()) {
            return "userEdit";
        }
        
        String errMgs = "";
        if (user.getPassword().equals(user.getConfirmPassword())) {
            Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));

            String img = (String) r.get("secure_url");
            System.out.println(img);
            user.setAvatar(img);
            if (this.userService.addOrUpdateUser(user) == true) {

                return "redirect:/admin/user-admin";
            } else {
                errMgs = "đã có lỗi";
            }
        } else {
            errMgs = " Mật khẩu không khớp";

        }

        model.addAttribute("errMsg", errMgs);
        
        return "redirect:/admin/user-admin";
    }
}
