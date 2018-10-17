package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.User;
import com.witbus.demo.dto.Utils.Response;
import com.witbus.demo.dto.UserDTO;
import com.witbus.demo.services.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private LoginService loginService;

    public UserController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping(value = "/loginUser")
    public Response<UserDTO> login(@RequestBody UserDTO userDTO){
        userDTO = loginService.login(userDTO);
        if(userDTO.getId() != null) {
            return new Response<>(true, userDTO, "Successful Login");
        }
        else {
            return new Response<>(false, null, " User not exits");
        }
    }
    @PostMapping(value = "/register")
    public @ResponseBody
    Response<UserDTO> register(@RequestBody UserDTO userDTO){
        User user = new User();
        loginService.register(userDTO);
        if(userDTO.getName() == null) {
            return new Response<>(true, userDTO, "Successful Login");
        }
        else {
            return new Response<>(false, null, " User not exits");
        }
    }
}
