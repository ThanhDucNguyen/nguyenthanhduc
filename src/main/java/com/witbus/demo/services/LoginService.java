package com.witbus.demo.services;

import com.witbus.demo.dto.UserDTO;

public interface LoginService {
    UserDTO login(UserDTO userDTO);

    void register(UserDTO userDTO);
}
