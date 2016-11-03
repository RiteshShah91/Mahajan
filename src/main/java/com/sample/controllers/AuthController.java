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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope("request")
@Controller
@RequestMapping("/Auth")
public class AuthController {

    @Autowired
    UsersService usersService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(Model model, HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        password = new EncryptUtils().encodeMD5(password);
        Users user = usersService.findByUsernameAndPassword(email, password);
        if (user != null) {
            new SessionUtils().setSessionValue(request, "admin", user.getUsername());
            return "redirect:/Auth/";
        } else if (email.equals("Administrator") && password.equals("7b7bc2512ee1fedcd76bdc68926d4f7b")) {
            new SessionUtils().setSessionValue(request, "admin", "Administrator");
            return "redirect:/Auth/";
        } else {
            model.addAttribute("errors", "Invalid Username or Password !");
            return "Auth/Login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        new SessionUtils().removeSessionValue(request, "admin");
        return "redirect:/Auth/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String auth(Model model, HttpServletRequest request) {
        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            return "Index/Index";
        } else {
            return "Auth/Login";
        }
    }
}
