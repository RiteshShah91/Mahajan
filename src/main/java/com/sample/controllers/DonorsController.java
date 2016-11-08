/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controllers;

import com.sample.entities.Donars;
import com.sample.services.DonarsService;
import com.sample.utils.CheckInput;
import com.sample.utils.DateUtils;
import com.sample.utils.SessionUtils;
import com.sample.utils.ValidateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope("request")
@Controller
@RequestMapping("/Donors")
public class DonorsController {

    @Autowired
    private DonarsService donarsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getList(Model model, HttpServletRequest request) {
//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            List<Donars> donarList = new ArrayList<>();
            long totalRecord = 0;
            if (new SessionUtils().getSessionValue(request, "queryParamMap") != null) {
                HashMap<String, String> paramMap = (HashMap<String, String>) new SessionUtils().getSessionValue(request, "queryParamMap");
                donarList = donarsService.findCountByFilterParameter(paramMap);
                totalRecord = donarList.size();
                donarList = donarsService.commonFindByFilterParameter(paramMap, 10, 0);
            } else {
                donarList = donarsService.findAll();
                totalRecord = donarsService.findCount();
            }

            model.addAttribute("donarsList", donarList);
            model.addAttribute("count", totalRecord);
            return "/Donors/List";
//        } else {
//            return "Auth/Login";
//        }
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String getResetList(Model model, HttpServletRequest request) {
//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            new SessionUtils().removeSessionValue(request, "queryParamMap");
            List<Donars> donarList = new ArrayList<>();
            long totalRecord = 0;
            donarList = donarsService.findAll();
            totalRecord = donarsService.findCount();
            model.addAttribute("donarsList", donarList);
            model.addAttribute("count", totalRecord);
            new SessionUtils().removeSessionValue(request, "queryParamMap");
            return "/Donors/List";
//        } else {
//            return "Auth/Login";
//        }
    }

    @RequestMapping(value = "/list_processing", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listProcessing(Model model, HttpServletRequest request) {

        JSONObject jSONObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
//            Enumeration<String> parameterNames = request.getParameterNames();
            int start = Integer.parseInt(request.getParameter("start"));
            int length = Integer.parseInt(request.getParameter("length"));
            int draw = Integer.parseInt(request.getParameter("draw"));
//            System.out.println("Start :" + start + "  Length : " + length);
//            while (parameterNames.hasMoreElements()) {
//                String paramName = parameterNames.nextElement();
//                String[] paramValues = request.getParameterValues(paramName);
//                for (String paramValue : paramValues) {
//                    System.out.println("Name :" + paramName + "  Value : " + paramValue);
//                }
//            }

            List<com.sample.entities.Donars> donarlist = null;
            long totalRecord = 0;
            if (new SessionUtils().getSessionValue(request, "queryParamMap") != null) {
                HashMap<String, String> paramMap = (HashMap<String, String>) new SessionUtils().getSessionValue(request, "queryParamMap");
                donarlist = donarsService.findCountByFilterParameter(paramMap);
                totalRecord = donarlist.size();
                donarlist = donarsService.commonFindByFilterParameter(paramMap, length, start);
            } else {
                donarlist = donarsService.findWithLimitAndOffset(length, start);
                totalRecord = donarsService.findCount();
            }
            for (com.sample.entities.Donars donar : donarlist) {
//                System.out.println("Id : " + donar.getId() + "\t Name : " + donar.getName());
                JSONArray jsonDataArray = new JSONArray();
                jsonDataArray.add(donar.getName());
                jsonDataArray.add(donar.getMobile());
                jsonDataArray.add(donar.getPhone());
                jsonDataArray.add(new DateUtils().dateWithFormat(donar.getDateOfFirstDonation(), "dd-MMMM-yyyy"));
                jsonDataArray.add("<a class=\"btn btn-md btn-info\" href=\"" + request.getContextPath() + "/Donors/edit/" + donar.getId() + "\">Edit</a> <a class=\"btn btn-md btn-warning\" href=\"" + request.getContextPath() + "/Donors/view/" + donar.getId() + "\">View</a> <a class=\"btn btn-md btn-danger\" onclick=\"return confirm('Are you sure to delete this Donor ?')\" href=\"" + request.getContextPath() + "/Donors/delete/" + donar.getId() + "\">Delete</a>");
                jsonArray.add(jsonDataArray);
            }
            jSONObject.put("draw", draw);
            jSONObject.put("recordsTotal", totalRecord);
            jSONObject.put("recordsFiltered", totalRecord);
            jSONObject.put("data", jsonArray);
//            System.out.println("Json Data : " + jSONObject.toString());
            return jSONObject;
//        }
//        return null;
    }

    @RequestMapping(value = "/postcreate", method = RequestMethod.POST)
    public String getPersonList(Model model, HttpServletRequest request) {
        ValidateUtils validateUtils = new ValidateUtils();
        CheckInput checkInput = new CheckInput();
        List<String> errors = new ArrayList<>();
        validateUtils.checkNull(request, "donarname", "Donor Name", errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "Donors/Create";
        }
        Donars donar = new Donars();
        donar.setName(request.getParameter("donarname"));
        donar.setPhone(checkInput.checkForNull(request.getParameter("phone")));
        donar.setMobile(checkInput.checkForNull(request.getParameter("mobile")));
        donar.setAddress(checkInput.checkForNull(request.getParameter("address")));
        donar.setDateOfFirstDonation(new DateUtils().stringToDate(request.getParameter("donation_date"), "dd-MMMM-yyyy"));
        donar.setCreatedDate(new Date());
        donar.setModifiedDate(new Date());
        donar.setStatus("Active");
        donar.setCreatedBy("Admin");
        donarsService.save(donar);
        model.addAttribute("m", "c");

        for (int i = 0; i < 5000; i++) {
            Donars donar1 = new Donars();
            donar1.setName(request.getParameter("donarname") + i);
            donar1.setPhone(request.getParameter("phone") + i);
            donar1.setMobile(request.getParameter("mobile"));
            donar1.setAddress(request.getParameter("address") + i);
            donar1.setDateOfFirstDonation(new DateUtils().stringToDate(request.getParameter("donation_date"), "dd-MMMM-yyyy"));
            donar1.setCreatedDate(new Date());
            donar1.setModifiedDate(new Date());
            donar1.setStatus("Active");
            donar1.setCreatedBy(new SessionUtils().getSessionValue(request, "admin").toString());
            donarsService.save(donar1);
            model.addAttribute("m", "c");
        }

        return "redirect:/Donors/";
    }
//

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Model model, HttpServletRequest request) {
//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            return "Donors/Create";
//        } else {
//            return "redirect:/Auth/";
//        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String preCreate(Model model, @PathVariable("id") String id, HttpServletRequest request) {

//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            Donars donar = new Donars();
            donar = donarsService.findById(id);
            if (donar != null) {
                model.addAttribute("donar", donar);
            }
            return "Donors/View";
//        } else {
//            return "redirect:/Auth/";
//        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id, HttpServletRequest request) {

//        if (new SessionUtils().getSessionValue(request, "admin") != null) {
            Donars donar = new Donars();
            donar = donarsService.findById(id);
            if (donar != null) {
                model.addAttribute("donar", donar);
            }
            return "Donors/Edit";
//        } else {
//            return "Auth/Login";
//        }
    }

    @RequestMapping(value = "/postedit/{id}", method = RequestMethod.POST)
    public String postEdit(Model model, HttpServletRequest request, @PathVariable("id") String id) {
        // model.addAttribute("personList", personService.listPerson());
        ValidateUtils validateUtils = new ValidateUtils();
        CheckInput checkInput = new CheckInput();
        List<String> errors = new ArrayList<>();
        Donars donar = donarsService.findById(id);
        if (donar != null) {
            validateUtils.checkNull(request, "donarname", "Donar Name", errors);
            if (!errors.isEmpty()) {
                model.addAttribute("errors", errors);
                model.addAttribute("donar", donar);
                return "Donors/Edit";
            }

            donar.setName(request.getParameter("donarname"));
            donar.setPhone(checkInput.checkForNull(request.getParameter("phone")));
            donar.setMobile(checkInput.checkForNull(request.getParameter("mobile")));
            donar.setAddress(checkInput.checkForNull(request.getParameter("address")));
            donar.setDateOfFirstDonation(new DateUtils().stringToDate(request.getParameter("donation_date"), "dd-MMMM-yyyy"));
            donar.setModifiedDate(new Date());
            donar.setModifiedBy(new SessionUtils().getSessionValue(request, "admin").toString());
            donarsService.save(donar);
            model.addAttribute("m", "e");
        }
        return "redirect:/Donors/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, HttpServletRequest request, @PathVariable("id") String id) {
        Donars donar = donarsService.findById(id);
        if (donar != null) {
            donar.setStatus("Deleted");
            donarsService.save(donar);
            model.addAttribute("m", "d");
        }
        return "redirect:/Donors/";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filterSearch(HttpServletRequest request, Model model) {
//        if (new SessionUtils().getSessionValue(request, "admin") != null) {

            HashMap<String, String> queryParamMap = new HashMap<>();
            if (request.getParameter("mobile") != null) {
                if (request.getParameter("mobile").trim().length() > 0) {
                    queryParamMap.put("mobile", request.getParameter("mobile"));
                }
            }
            if (request.getParameter("name") != null) {
                if (request.getParameter("name").trim().length() > 0) {
                    queryParamMap.put("donorname", request.getParameter("name"));
                }
            }
            if (request.getParameter("address") != null) {
                if (request.getParameter("address").trim().length() > 0) {
                    queryParamMap.put("address", request.getParameter("address"));
                }
            }
            new SessionUtils().setSessionValue(request, "queryParamMap", queryParamMap);
            List<Donars> donarList = donarsService.commonFindByFilterParameter(queryParamMap, 10, 0);
//            List<Donars> list = donarsService.findByFilterParameter(mobile, name, address);
            model.addAttribute("donarsList", donarList);
//            long totalRecord = donarsService.findCountByFilterParameter(queryParamMap);
//            model.addAttribute("count", totalRecord);
            return "Donors/List";
//        } else {
//            return "redirect:/Auth/";
//        }
    }

}
