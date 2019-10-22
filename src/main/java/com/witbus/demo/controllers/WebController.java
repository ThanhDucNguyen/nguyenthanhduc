package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.Product;
import com.witbus.demo.dto.ProductDTO;
import com.witbus.demo.dto.UserDTO;
import com.witbus.demo.services.WebService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class WebController {
    private WebService webService;

    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("products", webService.listProduct());
        return mav;
    }
    @GetMapping(value = "/detail-{id}-nguyenthanhdat-sangodanang")
    public ModelAndView detail(@PathVariable(value = "id") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("detail");
        mav.addObject("detail", webService.detailProduct(id));
        return mav;
    }
    @GetMapping(value = "/list-all-product-{type}-nguyenthanhdat-sangodanang")
    public ModelAndView listAllProduct(@PathVariable(value = "type") String type) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list-product");
        mav.addObject("products", webService.listAllProductByValue(type));
        return mav;
    }
    @GetMapping(value = "/list-product-{type}-nguyenthanhdat-sangodanang-{origin}")
    public ModelAndView listProduct(@PathVariable(value = "type") String type, @PathVariable(value = "origin") String origin) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list-product");
        mav.addObject("products", webService.listProductByValue(type, origin));
        return mav;
    }
    @PostMapping(value = "/send-mail")
    public String sendMail(String phone, String product){
        String send_mail = webService.sendMail(phone, product);
        return send_mail;
    }
    @GetMapping(value = "/login")
    public ModelAndView login(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") != null) {
            mav.setViewName("redirect:/admin");
        } else {
            mav.addObject("user", new UserDTO());
            mav.setViewName("login");
        }

        return mav;
    }
    @PostMapping(value = "/loginProcess")
    public ModelAndView loginProcess(UserDTO userDTO, HttpSession session) throws IOException {
        ModelAndView mav= new ModelAndView();
        mav.addObject("user",userDTO = webService.login(userDTO));
        if (userDTO.getId() != null) {
            session.setAttribute("user", userDTO);
            session.removeAttribute("error");
            mav.setViewName("redirect:/admin");
        } else {
            mav.addObject("user", new UserDTO());
            mav.setViewName("redirect:/login");
        }
        return mav;
    }
    @GetMapping(value = "/add-product")
    public ModelAndView addProduct() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        return mav;
    }
    @GetMapping(value = "/admin")
    public ModelAndView admin(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("products", webService.listProduct());
        mav.addObject("product", new ProductDTO());
        mav.setViewName("managerment");
        return mav;
    }
    @PostMapping(value = "/addProductProcess")
    public ModelAndView addProductProcess(ProductDTO productDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("product",webService.addProduct(productDTO));
        System.out.println("duc" + productDTO.getImage());
        mav.setViewName("redirect:/admin");
        return mav;
    }
    @GetMapping(value = "/addProduct")
    public ModelAndView addProduct(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("add", new ProductDTO());
        mav.setViewName("add");
        return mav;
    }
    @GetMapping(value = "admin/detail-{id}-nguyenthanhdat-sangodanang")
    public ModelAndView detailProduct(@PathVariable(value = "id") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("detail");
        mav.addObject("detail", webService.detailProduct(id));
        return mav;
    }
    @PostMapping(value = "/addd")
    public String addd(String image) throws IOException {
        System.out.println("duc" + image);
        return "duc";
    }
}
