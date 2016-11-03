/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.services;

import com.sample.entities.Users;
import java.util.List;
import org.springframework.context.annotation.Scope;
//import org.springframework.data.mongodb.core.MongoTemplate;

@Scope(value = "request")
public interface UsersService {

    public Users findByName(String firstName);

    public void save(Users user);

    public List<Users> findAll(String status);

    public Users findById(String id);

    public Users findByUsernameAndPassword(String email, String password);

}
