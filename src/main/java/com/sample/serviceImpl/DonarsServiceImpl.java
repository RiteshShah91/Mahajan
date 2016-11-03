/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.serviceImpl;

import com.sample.entities.Donars;
import com.sample.repositories.DonarsRepository;
import com.sample.services.DonarsService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonarsServiceImpl implements DonarsService {

    @Autowired
    DonarsRepository donarsRepository;

    public List<Donars> findAll() {
        return donarsRepository.findAll();
    }

    @Override
    public void save(Donars donar) {
        if (donar.getId() == null) {
            donar.setId(UUID.randomUUID() + "");
        }
        donarsRepository.save(donar);
    }

    @Override
    public Donars findById(String id) {
       return donarsRepository.findOne(id);
    }
}
