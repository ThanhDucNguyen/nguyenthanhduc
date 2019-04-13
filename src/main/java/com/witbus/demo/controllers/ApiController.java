package com.witbus.demo.controllers;

import com.witbus.demo.dto.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.witbus.demo.services.BookingService;
import org.springframework.web.bind.annotation.*;
import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.UserRepository;
import com.witbus.demo.services.LoginService;
import com.witbus.demo.services.OfferService;
import com.witbus.demo.services.SearchService;
import com.witbus.demo.services.SeatService;

import java.util.List;

@RequestMapping(value = "/api")
@RestController
public class ApiController {
    private BookingService bookingService;
    private UserRepository userRepository;
    private LoginService loginService;
    private OfferService offerService;
    private SearchService searchService;
    private SeatService seatService;

    public ApiController(BookingService bookingService, UserRepository userRepository, LoginService loginService, OfferService offerService, SearchService searchService, SeatService seatService) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
        this.loginService = loginService;
        this.offerService = offerService;
        this.searchService = searchService;
        this.seatService = seatService;
    }

//    @PostMapping(value = "/loginUser")
//    public Response<UserDTO> login(@RequestBody UserDTO userDTO){
//        userDTO = loginService.login(userDTO);
//        if(userDTO.getId() != null) {
//            return new Response<>(true, userDTO, "Successful Login");
//        }
//        else {
//            return new Response<>(false, null, " User not exits");
//        }
//    }
//    @PostMapping(value = "/register")
//    public @ResponseBody
//    Response<UserDTO> register(@RequestBody UserDTO userDTO){
//        User user = userRepository.checkUserName(userDTO.getName());
//        if (user == null){
//            loginService.register(userDTO);
//            return new Response<>(true, userDTO, "Successful Login");
//        }
//        else {
//            return new Response<>(false, null, " User not exits");
//        }
//    }

    // list cac offer
    @GetMapping(value = "/offer")
    public Response<List<OfferDTO>> busOwner() {
        List<OfferDTO> busOwnerDTOList = offerService.getOffer();
        return new Response<>(true, busOwnerDTOList, "Succsess full!");
    }
    // Danh sach nha xe
    @GetMapping(value = "/listBusOwner")
    public Response<List<BusOwnerDTO>> listBus() {
        List<BusOwnerDTO> busOwnerDTOS = searchService.listBusOwner();
        return new Response<>(true, busOwnerDTOS, "Succsess full!");
    }
    // Danh sach xe
    @RequestMapping(value = "/listBus/{id}", method = RequestMethod.POST)
    public Response<List<BusDTO>> listBusById(@PathVariable(value = "id") Long id){
        List<BusDTO> busDTOS = searchService.listBusById(id);
        return new Response<>(true,busDTOS,"Success list seat !");
    }
    // search xe
    @PostMapping(value = "/search")
    public Response<List<BusDTO>> searchTour(@RequestBody BusDTO busDTO) {
        List<BusDTO> busDTOList = searchService.searchTour(busDTO);
        return new Response<>(true, busDTOList, "Succsess full!");
    }
    // ho tro endpoint list xe, danh sach tuyen
    @GetMapping(value = "/listOriginDestination")
    public Response<List<BusDTO>> listOriginDestination() {
        List<BusDTO> busDTOS = searchService.listBus();
        return new Response<>(true, busDTOS, "Succsess full!");
    }

    // list cac ghe
    @RequestMapping(value = "/listSeat/{id}", method = RequestMethod.POST)
    public Response<List<SeatDTO>> seat(@PathVariable(value = "id") Long id){
        List<SeatDTO> seatDTOList = seatService.listSeat(id);
        return new Response<>(true,seatDTOList,"Success list seat !");
    }

    // booking
    @PostMapping(value = "/booking")
    public Response<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.booking(bookingDTO);
        return new Response<>(true, bookingDTO, "Successful!");
    }
    // checkEmail thuoc endpoint booking
    @PostMapping(value = "/checkEmail")
    public Response<BookingDTO> checkEmail(@RequestBody BookingDTO bookingDTO) {
        bookingService.checkEmail(bookingDTO);
        return new Response<>(true, bookingDTO, "Successful!");
    }
}
