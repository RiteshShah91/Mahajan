/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controllers;

import com.sample.entities.Users;
import com.sample.services.UsersService;
import com.sample.utils.EncryptUtils;
import com.sample.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope("request")
@Controller
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getList(Model model, HttpServletRequest request) {
        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            model.addAttribute("userList", usersService.findAll("Active"));
            return "/Users/List";
        } else {
            return "Auth/Login";
        }
    }

    @RequestMapping(value = "/postcreate", method = RequestMethod.POST)
    public String getPersonList(Model model, HttpServletRequest request) {
        // model.addAttribute("personList", personService.listPerson());

        Users user = new Users();
        user.setName(request.getParameter("name"));
        user.setPhone(request.getParameter("phone"));
        user.setUsername(request.getParameter("email"));
        user.setPassword(new EncryptUtils().encodeMD5(request.getParameter("password")));
        user.setStatus("Active");

        usersService.save(user);
        model.addAttribute("m", "c");
//        usersService.findByLastName("");
//        return usersService.findByLastName("").toString();
        return "redirect:/Users/";
    }

    @RequestMapping(value = "/precreate", method = RequestMethod.GET)
    public String preCreate(Model model, HttpServletRequest request) {
        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            return "Users/Create";
        } else {
            return "Auth/Login";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String preCreate(Model model, @PathVariable("id") String id, HttpServletRequest request) {

        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            Users user = new Users();
            user = usersService.findById(id);
            if (user != null) {
                model.addAttribute("user", user);
            }
            return "Users/Edit";
        } else {
            return "Auth/Login";
        }
    }

    @RequestMapping(value = "/post-edit/{id}", method = RequestMethod.POST)
    public String postEdit(Model model, HttpServletRequest request, @PathVariable("id") String id) {
        // model.addAttribute("personList", personService.listPerson());

        Users user = usersService.findById(id);
        if (user != null) {
            user.setName(request.getParameter("name"));
            user.setPhone(request.getParameter("phone"));
            user.setUsername(request.getParameter("email"));
            if (!user.getPassword().equals(request.getParameter("password"))) {
                user.setPassword(new EncryptUtils().encodeMD5(request.getParameter("password")));
            }
            user.setStatus("Active");
            usersService.save(user);
            model.addAttribute("m", "e");
        }
//        usersService.findByLastName("");
//        return usersService.findByLastName("").toString();
        return "redirect:/Users/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, HttpServletRequest request, @PathVariable("id") String id) {
        // model.addAttribute("personList", personService.listPerson());

        Users user = usersService.findById(id);
        if (user != null) {
            user.setStatus("Inactive");
            usersService.save(user);
            model.addAttribute("m", "d");
        }
//        usersService.findByLastName("");
//        return usersService.findByLastName("").toString();
        return "redirect:/Users/";
    }

}
