package com.witbus.demo.controllers;

import com.witbus.demo.dao.models.BusOwner;
import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.BusOwnerRepository;
import com.witbus.demo.dao.repository.UserRepository;
import com.witbus.demo.dto.*;
import com.witbus.demo.dto.Reponse.Response;
import com.witbus.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private  UserRepository userRepository;
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
    //----------------------------BusOwner-----------------------//
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
    @GetMapping(value = "/busOwner/{id}/delete")
    public Response<BusOwnerDTO> removeBusOwner(@PathVariable(value = "id") Long id){
        BusOwnerDTO busOwnerDTO = adminService.removeBusOwner(id);
        return new Response<>(true,busOwnerDTO,"Successful remove");
    }

    @PostMapping(value = "/busOwner/{id}/update")
    public Response<BusOwnerDTO>  updateBusOwnerProcess(@PathVariable(value = "id") Long id,@RequestBody BusOwnerDTO busOwnerDTO){
        busOwnerDTO = adminService.updateBusOwner(id,busOwnerDTO);
        return new Response<>(true, busOwnerDTO, "Successful full!");


    }
    @GetMapping(value = "/busOwner/{id}/detail")
    public Response<BusOwnerDTO> detailBusOwner(@PathVariable(value = "id") Long id) {
        BusOwnerDTO busOwnerDTO = adminService.detailBusOwner(id);
        return new Response<>(true, busOwnerDTO, "Succsess full!");
    }






    //-----------------------Bus-------------------------------------//
    @GetMapping(value = "/bus")
    public Response<List<BusDTO>> bus() {
        List<BusDTO> busSeatDTOList = adminService.listBus();
        return new Response<>(true, busSeatDTOList, "Succsess full!");
    }
    @PostMapping(value = "/busProcess")
    public Response<BusDTO> addBus(@RequestBody BusDTO busDTO){
        busDTO = adminService.addBus(busDTO);
        return new Response<>(true, busDTO, "Successful full!");
    }
    @GetMapping(value = "/bus/{id}/delete")
    public Response<BusDTO> removeBus(@PathVariable(value = "id") Long id){
        BusDTO busDTO = adminService.removeBus(id);
        return new Response<>(true,busDTO,"Successful remove");
    }

    @PutMapping(value = "/bus/{id}/update")
    public Response<BusDTO>  updateBusProcess(@PathVariable(value = "id") Long id,@RequestBody BusDTO busDTO){
        busDTO = adminService.updateBus(id,busDTO);
        return new Response<>(true, busDTO, "Successful full!");


    }
    @GetMapping(value = "/busList/{id}")
    public Response<List<BusDTO>> busList(@PathVariable(value = "id") Long id) {
        List<BusDTO> busDTOList = adminService.busListId(id);
        return new Response<>(true, busDTOList, "Succsess full!");
    }





    //---------------------------Seat-----------------------------------//
    @GetMapping(value = "/seat")
    public Response<List<SeatDTO>> seat() {
        List<SeatDTO> seatDTOList = adminService.listSeat();
        return new Response<>(true, seatDTOList, "Succsess full!");
    }
    @GetMapping(value = "/seatList/{id}")
    public Response<List<SeatDTO>> seatListId(@PathVariable(value = "id") Long id) {
        List<SeatDTO> seatDTOList = adminService.seatListId(id);
        return new Response<>(true, seatDTOList, "Succsess full!");
    }
    @PostMapping(value = "/seatProcess")
    public Response<SeatDTO> addSeatProcess(@RequestBody SeatDTO seatDTO){
        seatDTO = adminService.addSeat(seatDTO);

        return new Response<>(true, seatDTO, " Add Seat Successful full!");
    }
    @GetMapping(value = "/seat/{id}/delete")
    public Response<SeatDTO> removeSeat(@PathVariable(value = "id") Long id){
        SeatDTO seatDTO = adminService.removeSeat(id);
        return new Response<>(true,seatDTO,"Successful remove");
    }
    @PutMapping(value = "/seat/{id}/update")
    public Response<SeatDTO>  updateSeatProcess(@PathVariable(value = "id") Long id){
        SeatDTO seatDTO = adminService.updateSeat(id);
        return new Response<>(true, seatDTO, "Successful full!");
    }





    //------------------------------Users---------------------------------///
    @GetMapping(value = "/users")
    public Response<List<UserDTO>> users() {
        List<UserDTO> userDTOList = adminService.listUser();
        return new Response<>(true, userDTOList, "Succsess full!");
    }
    @PostMapping(value = "/usersProcess")
    public Response<UserDTO> addUsersProcess(@RequestBody UserDTO userDTO){
        User user = userRepository.findByUserExist(userDTO.getName());

        if (user ==null){
            adminService.addUser(userDTO);
            return new Response<>(true, userDTO, "Successful Register");
        }
        else {
            return new  Response<>(false,null,"Register fail because of User exist, please enter new Username");
        }
    }
    @GetMapping(value = "/users/{id}/delete")
    public Response<UserDTO> removeUser(@PathVariable(value = "id") Long id){
        UserDTO userDTO = adminService.removeUser(id);
        return new Response<>(true,userDTO,"Successful remove");
    }





    //------------------------------Offer---------------------------------///
    @GetMapping(value = "/offers")
    public Response<List<OfferDTO>> offers() {
        List<OfferDTO> offerDTOList = adminService.listOffer();
        return new Response<>(true, offerDTOList, "Succsess full!");
    }

    @PostMapping(value = "/offersProcess")
    public Response<OfferDTO> addOffersProcess(@RequestBody OfferDTO offerDTO){
        offerDTO = adminService.addOffer(offerDTO);
        return new Response<>(true, offerDTO, "Successful full!");
    }
    @GetMapping(value = "/offers/{id}/delete")
    public Response<OfferDTO> removeOffer(@PathVariable(value = "id") Long id){
        OfferDTO offerDTO = adminService.removeOffer(id);
        return new Response<>(true,offerDTO,"Successful remove");
    }
    @PutMapping(value = "/offers/{id}/update")
    public Response<OfferDTO>  updateOfferProcess(@PathVariable(value = "id") Long id){
        OfferDTO offerDTO = adminService.updateOffer(id);
        return new Response<>(true, offerDTO, "Successful full!");
    }






    //------------------------------Booking---------------------------------///
    @GetMapping(value = "/booking")
    public Response<List<BookingDTO>> booking() {
        List<BookingDTO> bookingDTOList = adminService.listBooking();
        return new Response<>(true, bookingDTOList, "Succsess full!");
    }
    @GetMapping(value = "/bookingList/{id}")
    public Response<List<BookingDTO>> bookingListId(@PathVariable(value = "id") Long id) {
        List<BookingDTO> bookingDTOS = adminService.bookingListId(id);
        return new Response<>(true, bookingDTOS, "Succsess full!");
    }
    @GetMapping(value = "/bookingListBySeat/{id}")
    public Response<List<BookingDTO>> bookingListBySeat(@PathVariable(value = "id") Long id) {
        List<BookingDTO> bookingDTOS = adminService.bookingListBySeatId(id);
        return new Response<>(true, bookingDTOS, "Succsess full!");
    }

    @GetMapping(value = "/booking/{id}/delete")
    public Response<BookingDTO> removeBooking(@PathVariable(value = "id") Long id){
        BookingDTO bookingDTO = adminService.removeBooking(id);
        return new Response<>(true,bookingDTO,"Successful remove");
    }


}
