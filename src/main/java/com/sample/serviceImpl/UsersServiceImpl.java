/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.serviceImpl;

import com.sample.entities.Users;
import com.sample.repositories.UsersRepository;
import com.sample.services.UsersService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users findByName(String firstName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Users user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID() + "");
        }
        usersRepository.save(user);
    }

    @Override
    public List<Users> findAll(String status) {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(String id) {
        return usersRepository.findOne(id);
    }

    @Override
    public Users findByUsernameAndPassword(String email, String password) {
        return usersRepository.findByUsernameAndPassword(email, password);
    }
}
