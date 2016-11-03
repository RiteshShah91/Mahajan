/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.repositories;

import com.sample.entities.Donars;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface DonarsRepository extends JpaRepository<Donars, String> {

    @Query("select d from Donars d where d.status != 'Deleted' order by d.CreatedDate desc")
    public List<Donars> findAll();

}
