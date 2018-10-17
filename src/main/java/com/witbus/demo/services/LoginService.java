package com.witbus.demo.services;

import com.witbus.demo.dao.models.User;
import com.witbus.demo.dto.UserDTO;

public interface LoginService {
    UserDTO login(UserDTO userDTO);

    UserDTO register(UserDTO userDTO);
}
