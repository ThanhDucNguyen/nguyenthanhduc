package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.Offer;
import com.witbus.demo.dto.*;
import com.witbus.demo.dto.Utils.Response;
import com.witbus.demo.services.AdminService;
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
    @GetMapping(value = "/bus")
    public Response<List<BusDTO>> bus() {
        List<BusDTO> busSeatDTOList = adminService.listBus();
        return new Response<>(true, busSeatDTOList, "Succsess full!");
    }
    @PostMapping(value = "/bus-Process")
    public Response<BusDTO> addBuss(@RequestBody BusDTO busDTO){
        busDTO = adminService.addBus(busDTO);
        return new Response<>(true, busDTO, "Successful full!");
    }
    @GetMapping(value = "/seat")
    public Response<List<SeatDTO>> seat() {
        List<SeatDTO> seatDTOList = adminService.listSeat();
        return new Response<>(true, seatDTOList, "Succsess full!");
    }
    @GetMapping(value = "/listOffer")
    public Response<List<OfferDTO>> offer() {
        List<OfferDTO> offerDTOList = adminService.listOffer();
        return new Response<>(true, offerDTOList, "Succsess full!");
    }
    @PostMapping(value = "/offerProcess")
    public Response<OfferDTO> addOffer(@RequestBody OfferDTO offerDTO){
        offerDTO = adminService.addOffer(offerDTO);
        return new Response<>(true, offerDTO, "Successful full!");
    }
}
