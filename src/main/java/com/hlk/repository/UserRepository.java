/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository;

import com.hlk.model.User;
import java.util.List;

/**
 *
 * @author MSI GE66
 */
public interface UserRepository {
    List<User> getUsers(String kw);
    User getUserById(int id);
    User getUserByUsername(String username);
    boolean deleteUser(int id);
    boolean addOrUpdateUser(User user);
}
