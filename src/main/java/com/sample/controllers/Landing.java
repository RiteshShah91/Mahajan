/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 1008
 */
@Scope("request")
@Controller
public class Landing {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) {
        System.out.println("From landing controler");
        return "Auth/Login";
    }
    
    @RequestMapping(value = "/Next", method = RequestMethod.GET)
    public String next(Model model, HttpServletRequest request) {
        System.out.println("From Next controler");
        return "Next";
    }

}
