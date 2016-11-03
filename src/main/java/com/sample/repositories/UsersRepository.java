/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.repositories;

import com.sample.entities.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    public Users findByName(String name);

    public List<Users> findAll();

    public Users findByUsernameAndPassword(String email, String password);

}
