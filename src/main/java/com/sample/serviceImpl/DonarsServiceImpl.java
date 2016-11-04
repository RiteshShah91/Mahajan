/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.serviceImpl;

import com.sample.entities.Donars;
import com.sample.repositories.DonarsRepository;
import com.sample.services.DonarsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonarsServiceImpl implements DonarsService {

    @Autowired
    DonarsRepository donarsRepository;

    @PersistenceContext(unitName = "dw")
    private EntityManager entityManager;

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

    @Override
    public List<Donars> findWithLimitAndOffset(int length, int start) {
        return donarsRepository.findWithLimitAndOffset(length, start);
    }

    @Override
    public List<Donars> findByFilterParameter(String mobile, String name, String address) {

        HashMap<String, String> paramMap = new HashMap<>();
        StringBuilder query = new StringBuilder("select * from donars d where");

        query.append(" d.status != :status");
        paramMap.put("status", "Deleted");

        if (!(mobile == null || mobile.equals(""))) {
            query.append(" and d.mobile LIKE :mobile");
            paramMap.put("mobile", "%" + mobile + "%");
        }

        if (!(name == null || name.equals(""))) {
            String[] combineName = name.split("\\s");
            if (combineName.length > 1) {
                query.append(" and UPPER(d.name) LIKE :fname");
                paramMap.put("fname", "%" + combineName[0].toUpperCase() + "%");
            } else {
                query.append(" and UPPER(d.name) LIKE :sname ");
                paramMap.put("sname", "%" + name.toUpperCase() + "%");
            }
        }

        if (!(address == null || address.equals(""))) {
            query.append(" and UPPER(d.address) LIKE :address");
            paramMap.put("address", "%" + address.toUpperCase() + "%");
        }

        query.append(" order by d.createddate desc");

        Query q = entityManager.createNativeQuery(query.toString(), Donars.class);
        for (Map.Entry pair : paramMap.entrySet()) {
            q.setParameter(pair.getKey().toString(), pair.getValue());
        }

        return q.getResultList();
    }

}
