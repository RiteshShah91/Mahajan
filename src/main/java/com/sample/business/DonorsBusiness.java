/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.business;

import com.sample.entities.Donars;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author 1008
 */
public class DonorsBusiness {

    @PersistenceContext(unitName = "dw")
    private EntityManager entityManager;

    public List<Donars> generateQuery(HashMap<String, String> paramMap, int limit, int offset) {

        HashMap<String, String> queryParamMap = new HashMap<>();
        StringBuilder query = new StringBuilder("select * from donars d where");

        query.append(" d.status != :status");
        queryParamMap.put("status", "Deleted");

        if (!(paramMap.get("mobile") == null || paramMap.get("mobile").equals(""))) {
            query.append(" and d.mobile LIKE :mobile");
            queryParamMap.put("mobile", "%" + paramMap.get("mobile") + "%");
        }

        if (!(paramMap.get("donarname") == null || paramMap.get("donarname").equals(""))) {
            String[] combineName = paramMap.get("donarname").split("\\s");
            if (combineName.length > 1) {
                query.append(" and UPPER(d.name) LIKE :fname");
                queryParamMap.put("fname", "%" + combineName[0].toUpperCase() + "%");
            } else {
                query.append(" and UPPER(d.name) LIKE :sname ");
                queryParamMap.put("sname", "%" + paramMap.get("donarname").toUpperCase() + "%");
            }
        }

        if (!(paramMap.get("address") == null || paramMap.get("address").equals(""))) {
            query.append(" and UPPER(d.address) LIKE :address");
            queryParamMap.put("address", "%" + paramMap.get("address").toUpperCase() + "%");
        }

        query.append(" order by d.createddate desc limit ").append(limit).append(" offset ").append(offset);
        System.out.println("Query : " + query.toString());
        
        Query q = entityManager.createNativeQuery(query.toString(), Donars.class);
        for (Map.Entry pair : queryParamMap.entrySet()) {
            q.setParameter(pair.getKey().toString(), pair.getValue());
        }

        return q.getResultList();

    }

}
