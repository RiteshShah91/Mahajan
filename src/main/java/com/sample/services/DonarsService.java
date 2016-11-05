/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.services;

import com.sample.entities.Donars;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Scope;
//import org.springframework.data.mongodb.core.MongoTemplate;

@Scope(value = "request")
public interface DonarsService {

    public List<Donars> findAll();

    public void save(Donars donar);

    public Donars findById(String id);

    public List<Donars> findWithLimitAndOffset(int length, int start);

    public List<Donars> findByFilterParameter(String mobile, String name, String address);
    
    public List<Donars> commonFindByFilterParameter(HashMap<String, String> paramMap, int limit, int offset);

    public long findCount();


}
