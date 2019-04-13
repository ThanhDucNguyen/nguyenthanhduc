package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.services.AdminService;
import com.witbus.demo.services.WebService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ManagerOwnerController {
    private AdminService adminService;
    private WebService webService;

    public ManagerOwnerController(AdminService adminService, WebService webService) {
        this.adminService = adminService;
        this.webService = webService;
    }

    @GetMapping(value = "/login-owner")
    public ModelAndView loginManager(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") != null) {
            mav.setViewName("redirect:/quan-ly-xe");
        } else {
            mav.addObject("owner", new BusOwnerDTO());
            mav.setViewName("loginOwner");
        }
        return mav;
    }
    @PostMapping(value = "/loginManagerProcess")
    public ModelAndView loginProcess(BusOwnerDTO busOwnerDTO, HttpSession session) throws IOException {
        ModelAndView mav= new ModelAndView();
        mav.addObject("abc",busOwnerDTO = webService.loginManager(busOwnerDTO));
        if (busOwnerDTO.getId() != null) {
            session.setAttribute("owner", busOwnerDTO);
            session.removeAttribute("error");
            mav.addObject("list", busOwnerDTO);
            mav.setViewName("redirect:/quan-ly-xe");
        } else {
//            session.setAttribute("error", userDTO.getMessage());
            mav.addObject("owner", new BusOwnerDTO());
            mav.setViewName("redirect:/quan-ly-xe");
        }
        return mav;
    }
    @GetMapping(value = "/quan-ly-xe")
    public ModelAndView indexManager(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") == null) {
            mav.setViewName("redirect:/login-owner");
        } else {
            mav.addObject("owner", new BusOwnerDTO());
            mav.setViewName("indexManager");
        }
        return mav;
    }
    @GetMapping(value = "/danh-sach-xe-28-wit-0{id}-bus-43")
    public ModelAndView danhSachXe(@PathVariable(value = "id")Long id, HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") != null) {
            mav.setViewName("redirect:/owner");
        }
        mav.addObject("list",webService.listXe(id));
        mav.setViewName("listBusOwner");
        return mav;
    }
    @GetMapping(value = "/manager-addBus")
    public ModelAndView addBus(HttpSession session,BusOwnerDTO busOwnerDTO) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") == null) {
            mav.setViewName("redirect:/owner");
        } else {
            mav.addObject("busOwner", adminService.listBusOwner());
            mav.addObject("bus", new BusDTO());
            mav.setViewName("addBusOfOwner");
        }

        return mav;
    }
    @PostMapping(value = "/manager-busProcess")
    public ModelAndView addBusProcess(BusDTO busDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") == null){
            mav.setViewName("redirect:/owner");
            return mav;
        }
        mav.addObject("bus",webService.addBus(busDTO));
        mav.setViewName("redirect:/quan-ly-xe");
        return mav;
    }
    @GetMapping(value = "/manager-removeBus/{id}/delete")
    public ModelAndView removeBus(@PathVariable(value = "id") Long id, BusOwnerDTO busOwnerDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("bus",adminService.removeBus(id));
        mav.setViewName("redirect:/bus");
        return mav;
    }
    @GetMapping(value = "/manager-detailBus-{id}")
    public ModelAndView detailBus(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") == null){
            mav.setViewName("redirect:/owner");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Bus bus = adminService.detailBus(id);
        mav.addObject("edit",bus);

        mav.setViewName("detailBusManager");
        return mav;
    }
    @GetMapping(value = "/manager-updateBus-{id}")
    public ModelAndView updateBus(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") == null){
            mav.setViewName("redirect:/owner");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Bus bus = adminService.detailBus(id);
        if(bus != null){
            mav.addObject("busOwner", adminService.listBusOwner());
            mav.addObject("edit",bus);
        }

        mav.setViewName("editBusManager");
        return mav;
    }

    @PostMapping(value = "/manager-updateBus-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateBusProcess( BusDTO busDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateBus(busDTO);
        mav.setViewName("redirect:/owner");
        return mav;
    }
    @GetMapping(value = "/danh-sach-ghe-28-wit-0-bus-43--JKEWHREF-FEF-SDFSD-FBTBWVH-SDF{id}-EFEWF54-DSFF")
    public ModelAndView danhSachGhe(@PathVariable(value = "id")Long id, HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("owner") != null) {
            mav.setViewName("redirect:/owner");
        }
        mav.addObject("listSeat",webService.listGhe(id));
        mav.setViewName("seatManager");
        return mav;
    }
}
