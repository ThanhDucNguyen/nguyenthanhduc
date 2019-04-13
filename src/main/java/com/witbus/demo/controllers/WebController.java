package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.dto.HelpDTO;
import com.witbus.demo.services.AdminService;
import com.witbus.demo.services.WebService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class WebController {
    private AdminService adminService;
    private WebService webService;

    public WebController(AdminService adminService, WebService webService) {
        this.adminService = adminService;
        this.webService = webService;
    }


    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("landingpage");
        mav.addObject("busOwner", adminService.listBusOwner());
        mav.addObject("send", new HelpDTO());
        return mav;
    }
    @PostMapping(value = "/help")
    public ModelAndView help(HelpDTO helpDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("send",adminService.sendHelp(helpDTO));
        mav.setViewName("redirect:/");
        return mav;
    }
}
