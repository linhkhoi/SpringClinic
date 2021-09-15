/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.service;

import com.hlk.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author MSI GE66
 */
public interface UserService extends UserDetailsService {
    List<User> getUsers(String kw);
    User getUserById(int id);
    boolean deleteUser(int id);
    boolean addOrUpdateUser(User user);
    User getUserByUsername(String username);
}
