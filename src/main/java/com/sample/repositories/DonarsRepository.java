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

    @Query(nativeQuery = true, value = "select * from donars d where d.status != 'Deleted' order by d.createddate desc LIMIT 10 OFFSET 0")
    public List<Donars> findAll();

    @Query(nativeQuery = true, value = "select * from donars d where d.status != 'Deleted' order by d.createddate desc LIMIT ?1 OFFSET ?2")
    public List<Donars> findWithLimitAndOffset(int length, int start);

}
