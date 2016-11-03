/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controllers;

import com.sample.entities.Donars;
import com.sample.services.DonarsService;
import com.sample.utils.DateUtils;
import com.sample.utils.SessionUtils;
import com.sample.utils.ValidateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/Donars")
public class DonarsController {

    @Autowired
    private DonarsService donarsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getList(Model model, HttpServletRequest request) {
        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            model.addAttribute("donarsList", donarsService.findAll());
            return "/Donars/List";
        } else {
            return "Auth/Login";
        }
    }

    @RequestMapping(value = "/postcreate", method = RequestMethod.POST)
    public String getPersonList(Model model, HttpServletRequest request) {
        ValidateUtils validateUtils = new ValidateUtils();
        List<String> errors = new ArrayList<>();
        validateUtils.checkNull(request, "donarname", "Donar Name", errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "Donars/Create";
        }
        Donars donar = new Donars();
        donar.setName(request.getParameter("donarname"));
        donar.setPhone(request.getParameter("phone"));
        donar.setMobile(request.getParameter("mobile"));
        donar.setAddress(request.getParameter("address"));
        donar.setDateOfFirstDonation(new DateUtils().stringToDate(request.getParameter("donation_date"), "dd-MMMM-yyyy"));
        donar.setCreatedDate(new Date());
        donar.setModifiedDate(new Date());
        donar.setStatus("Active");
        donar.setCreatedBy(new SessionUtils().getSessionValue(request, "admin").toString());
        donarsService.save(donar);
        model.addAttribute("m", "c");
        return "redirect:/Donars/";
    }
//

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Model model, HttpServletRequest request) {
        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            return "Donars/Create";
        } else {
            return "redirect:/Auth/";
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String preCreate(Model model, @PathVariable("id") String id, HttpServletRequest request) {

        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            Donars donar = new Donars();
            donar = donarsService.findById(id);
            if (donar != null) {
                model.addAttribute("donar", donar);
            }
            return "Donars/View";
        } else {
            return "redirect:/Auth/";
        }
    }

//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String preCreate(Model model, @PathVariable("id") String id, HttpServletRequest request) {
//
//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
//            Users user = new Users();
//            user = donarsService.findById(id);
//            if (user != null) {
//                model.addAttribute("user", user);
//            }
//            return "Users/Edit";
//        } else {
//            return "Auth/Login";
//        }
//    }
//    @RequestMapping(value = "/post-edit/{id}", method = RequestMethod.POST)
//    public String postEdit(Model model, HttpServletRequest request, @PathVariable("id") String id) {
//        // model.addAttribute("personList", personService.listPerson());
//
//        Users user = donarsService.findById(id);
//        if (user != null) {
//            user.setName(request.getParameter("name"));
//            user.setPhone(request.getParameter("phone"));
//            user.setUsername(request.getParameter("email"));
//            if (!user.getPassword().equals(request.getParameter("password"))) {
//                user.setPassword(new EncryptUtils().encodeMD5(request.getParameter("password")));
//            }
//            user.setStatus("Active");
//            donarsService.save(user);
//            model.addAttribute("m", "e");
//        }
////        usersService.findByLastName("");
////        return usersService.findByLastName("").toString();
//        return "redirect:/Users/";
//    }
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public String delete(Model model, HttpServletRequest request, @PathVariable("id") String id) {
//        // model.addAttribute("personList", personService.listPerson());
//
//        Users user = donarsService.findById(id);
//        if (user != null) {
//            user.setStatus("Inactive");
//            donarsService.save(user);
//            model.addAttribute("m", "d");
//        }
////        usersService.findByLastName("");
////        return usersService.findByLastName("").toString();
//        return "redirect:/Users/";
//    }
}
