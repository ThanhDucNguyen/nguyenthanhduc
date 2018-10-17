package com.witbus.demo.services;

import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.UserRepository;
import com.witbus.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceimpl implements LoginService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.findByUserNameAndPassword(userDTO.getName(),userDTO.getPassword());
        if (user != null){
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setPhone(user.getPhone());
            userDTO.setEmail(user.getEmail());
        }
        return userDTO;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        User user1 = userRepository.checkUserName(userDTO.getName());
        if (user1 == null){
            User user = new User();
            user.setId(userDTO.getId());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
        }
        return null;
    }
}
