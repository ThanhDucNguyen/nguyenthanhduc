package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.BusOwner;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.dto.Reponse.Response;
import com.witbus.demo.dto.SeatDTO;
import com.witbus.demo.dto.UserDTO;
import com.witbus.demo.services.AdminService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping(value = "/login")
    public Response<UserDTO> login(@RequestBody UserDTO userDTO){
        userDTO = adminService.login(userDTO);
        if(userDTO.getId() != null) {
            return new Response<>(true, userDTO, "Successful Login");
        }
        else {
            return new Response<>(false, null, " User not exits");
        }
    }
    @GetMapping(value = "/users")
    public Response<List<UserDTO>> getUsers() {
        List<UserDTO> users = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("admin");
        users.add(userDTO);



        return new Response<>(true, users, "");
    }
    @GetMapping(value = "/busOwner")
    public Response<List<BusOwnerDTO>> busOwner() {
        List<BusOwnerDTO> busOwnerDTOList = adminService.listBusOwner();
        return new Response<>(true, busOwnerDTOList, "Succsess full!");
    }
    @PostMapping(value = "/busOwnerProcess")
    public Response<BusOwnerDTO> addBusOwner(@RequestBody BusOwnerDTO busOwnerDTO){
        busOwnerDTO = adminService.addBusOwner(busOwnerDTO);
        return new Response<>(true, busOwnerDTO, "Successful full!");
    }
//    @GetMapping("/busOwnerDelete")
//    public Response<BusOwnerDTO> delete(@PathVariable Long id,BusOwnerDTO busOwnerDTO) {
//        busOwnerDTO = adminService.delete(id);
//        return new Response<>(true, busOwnerDTO, "Successful full!");
//    }
    @GetMapping(value = "/bus")
    public Response<List<BusDTO>> bus() {
        List<BusDTO> busSeatDTOList = adminService.listBus();
        return new Response<>(true, busSeatDTOList, "Succsess full!");
    }
    @GetMapping(value = "/seat")
    public Response<List<SeatDTO>> seat() {
        List<SeatDTO> seatDTOList = adminService.listSeat();
        return new Response<>(true, seatDTOList, "Succsess full!");
    }
}
