package com.witbus.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dao.models.BusOwner;
import com.witbus.demo.dao.models.Seat;
import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.BusOwnerRepository;
import com.witbus.demo.dao.repository.UserRepository;
import com.witbus.demo.dto.*;
import com.witbus.demo.dto.Reponse.Response;
import com.witbus.demo.services.AdminService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    private AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/admin")
    public ModelAndView index(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("index");
        return mav;
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
        mav.addObject("user",userDTO = adminService.login(userDTO));
        if (userDTO.getId() != null) {
            session.setAttribute("user", userDTO);
            session.removeAttribute("error");
            mav.setViewName("redirect:/admin");
        } else {
//            session.setAttribute("error", userDTO.getMessage());
            mav.addObject("user", new UserDTO());
            mav.setViewName("redirect:/login");
        }
        return mav;
    }
    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        session.removeAttribute("user");
        mav.setViewName("redirect:/login");
        return mav;
    }

    //----------------------------BusOwner-----------------------//

    @GetMapping(value = "/busOwner")
    public ModelAndView listBusOwner(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        mav.addObject("busOwner", adminService.listBusOwner());

        mav.setViewName("busowner");
        return mav;
    }

    @GetMapping(value = "/addBusOwner")
    public ModelAndView addBusOwner(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null) {
            mav.setViewName("redirect:/");
        } else {
            mav.addObject("busOwner", new BusOwnerDTO());
            mav.setViewName("addBusOwner");
        }

        return mav;
    }
    @PostMapping(value = "/busOwnerProcess")
    public ModelAndView addBusOwnerProcess(BusOwnerDTO busOwnerDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("busOwner",adminService.addBusOwner(busOwnerDTO));
        mav.setViewName("redirect:/busOwner");
        return mav;
    }
    @GetMapping(value = "/removeBusOwner/{id}/delete")
    public ModelAndView removeBusOwner(@PathVariable(value = "id") Long id, BusOwnerDTO busOwnerDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("busOwner",adminService.removeBusOwner(id));
        mav.setViewName("redirect:/busOwner");
        return mav;
    }
    @GetMapping(value = "/admin-detailBusOwner-{id}")
    public ModelAndView detailBusOwner(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        BusOwner busOwner = adminService.detailBusOwner(id);
        mav.addObject("edit",busOwner);

        mav.setViewName("detailBusOwner");
        return mav;
    }
    @GetMapping(value = "/admin-updateBusOwner-{id}")
    public ModelAndView updateBusOwner(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        BusOwner busOwner = adminService.detailBusOwner(id);
        if(busOwner != null){
            mav.addObject("edit",busOwner);
        }

        mav.setViewName("editBusOwner");
        return mav;
    }

    @PostMapping(value = "/admin-updateBusOwner-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateBusOwnerProcess( BusOwnerDTO busOwnerDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateBusOwner(busOwnerDTO);
        mav.setViewName("redirect:/busOwner");
        return mav;
    }

    //-----------------------Bus-------------------------------------//

    @GetMapping(value = "/bus")
    public ModelAndView listBus(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        mav.addObject("bus", adminService.listBus());

        mav.setViewName("bus");
        return mav;
    }

    @GetMapping(value = "/addBus")
    public ModelAndView addBus(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null) {
            mav.setViewName("redirect:/");
        } else {
            mav.addObject("bus", new BusDTO());
            mav.setViewName("addBus");
        }

        return mav;
    }
    @PostMapping(value = "/busProcess")
    public ModelAndView addBusProcess(BusDTO busDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("bus",adminService.addBus(busDTO));
        mav.setViewName("redirect:/bus");
        return mav;
    }
    @GetMapping(value = "/removeBus/{id}/delete")
    public ModelAndView removeBus(@PathVariable(value = "id") Long id, BusOwnerDTO busOwnerDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("bus",adminService.removeBus(id));
        mav.setViewName("redirect:/bus");
        return mav;
    }
    @GetMapping(value = "/admin-detailBus-{id}")
    public ModelAndView detailBus(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Bus bus = adminService.detailBus(id);
        mav.addObject("edit",bus);

        mav.setViewName("detailBus");
        return mav;
    }
    @GetMapping(value = "/admin-updateBus-{id}")
    public ModelAndView updateBus(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Bus bus = adminService.detailBus(id);
        if(bus != null){
            mav.addObject("edit",bus);
        }

        mav.setViewName("editBus");
        return mav;
    }

    @PostMapping(value = "/admin-updateBus-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateBusProcess( BusDTO busDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateBus(busDTO);
        mav.setViewName("redirect:/bus");
        return mav;
    }

    //---------------------------Seat-----------------------------------//

    @GetMapping(value = "/seat")
    public ModelAndView listSeat(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        mav.addObject("seats", adminService.listSeat());

        mav.setViewName("seat");
        return mav;
    }

    @GetMapping(value = "/addSeat")
    public ModelAndView addSeat(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null) {
            mav.setViewName("redirect:/");
        } else {
            mav.addObject("seat", new SeatDTO());
            mav.setViewName("addSeat");
        }

        return mav;
    }
    @PostMapping(value = "/seatProcess")
    public ModelAndView addSeatProcess(SeatDTO seatDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("seat",adminService.addSeat(seatDTO));
        mav.setViewName("redirect:/seat");
        return mav;
    }
    @GetMapping(value = "/removeSeat/{id}/delete")
    public ModelAndView removeSeat(@PathVariable(value = "id") Long id, SeatDTO seatDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("seat",adminService.removeSeat(id));
        mav.setViewName("redirect:/seat");
        return mav;
    }
    @GetMapping(value = "/admin-detailSeat-{id}")
    public ModelAndView detailSeat(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Seat seat = adminService.detailSeat(id);
        mav.addObject("edit",seat);

        mav.setViewName("detailSeat");
        return mav;
    }
    @GetMapping(value = "/admin-updateSeat-{id}")
    public ModelAndView updateSeat(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Seat seat = adminService.detailSeat(id);
        if(seat != null){
            mav.addObject("edit",seat);
        }

        mav.setViewName("editSeat");
        return mav;
    }

    @PostMapping(value = "/admin-updateSeat-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateSeatProcess( SeatDTO seatDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateSeat(seatDTO);
        mav.setViewName("redirect:/seat");
        return mav;
    }
//    @GetMapping(value = "/seat")
//    public Response<List<SeatDTO>> seat() {
//        List<SeatDTO> seatDTOList = adminService.listSeat();
//        return new Response<>(true, seatDTOList, "Succsess full!");
//    }
//
//    @GetMapping(value = "/seatList/{id}")
//    public Response<List<SeatDTO>> seatListId(@PathVariable(value = "id") Long id) {
//        List<SeatDTO> seatDTOList = adminService.seatListId(id);
//        return new Response<>(true, seatDTOList, "Succsess full!");
//    }
//
//    @PostMapping(value = "/seatProcess")
//    public Response<SeatDTO> addSeatProcess(@RequestBody SeatDTO seatDTO) {
//        seatDTO = adminService.addSeat(seatDTO);
//
//        return new Response<>(true, seatDTO, " Add Seat Successful full!");
//    }
//
//    @GetMapping(value = "/seat/{id}/delete")
//    public Response<SeatDTO> removeSeat(@PathVariable(value = "id") Long id) {
//        SeatDTO seatDTO = adminService.removeSeat(id);
//        return new Response<>(true, seatDTO, "Successful remove");
//    }
////    @PutMapping(value = "/seat/{id}/update")
////    public Response<SeatDTO>  updateSeatProcess(@PathVariable(value = "id") Long id){
////        SeatDTO seatDTO = adminService.updateSeat(id);
////        return new Response<>(true, seatDTO, "Successful full!");
////    }

    //------------------------------Users---------------------------------///
    @GetMapping(value = "/users")
    public Response<List<UserDTO>> users() {
        List<UserDTO> userDTOList = adminService.listUser();
        return new Response<>(true, userDTOList, "Succsess full!");
    }

    @PostMapping(value = "/usersProcess")
    public Response<UserDTO> addUsersProcess(@RequestBody UserDTO userDTO) {
        User user = userRepository.findByUserExist(userDTO.getName());

        if (user == null) {
            adminService.addUser(userDTO);
            return new Response<>(true, userDTO, "Successful Register");
        } else {
            return new Response<>(false, null, "Register fail because of User exist, please enter new Username");
        }
    }

    @GetMapping(value = "/users/{id}/delete")
    public Response<UserDTO> removeUser(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = adminService.removeUser(id);
        return new Response<>(true, userDTO, "Successful remove");
    }

    //------------------------------Offer---------------------------------///
    @GetMapping(value = "/offers")
    public Response<List<OfferDTO>> offers() {
        List<OfferDTO> offerDTOList = adminService.listOffer();
        return new Response<>(true, offerDTOList, "Succsess full!");
    }

    @PostMapping(value = "/offersProcess")
    public Response<OfferDTO> addOffersProcess(@RequestBody OfferDTO offerDTO) {
        offerDTO = adminService.addOffer(offerDTO);
        return new Response<>(true, offerDTO, "Successful full!");
    }

    @GetMapping(value = "/offers/{id}/delete")
    public Response<OfferDTO> removeOffer(@PathVariable(value = "id") Long id) {
        OfferDTO offerDTO = adminService.removeOffer(id);
        return new Response<>(true, offerDTO, "Successful remove");
    }
//    @PutMapping(value = "/offers/{id}/update")
//    public Response<OfferDTO>  updateOfferProcess(@PathVariable(value = "id") Long id){
//        OfferDTO offerDTO = adminService.updateOffer(id);
//        return new Response<>(true, offerDTO, "Successful full!");
//    }


    //------------------------------Booking---------------------------------///
    @GetMapping(value = "/booking")
    public Response<List<BookingDTO>> booking() {
        List<BookingDTO> bookingDTOList = adminService.listBooking();
        return new Response<>(true, bookingDTOList, "Succsess full!");
    }

    @GetMapping(value = "/bookingListBySeat/{id}")
    public Response<List<BookingDTO>> bookingListBySeat(@PathVariable(value = "id") Long id) {
        List<BookingDTO> bookingDTOS = adminService.bookingListBySeatId(id);
        return new Response<>(true, bookingDTOS, "Succsess full!");
    }

    @GetMapping(value = "/booking/{id}/delete")
    public Response<BookingDTO> removeBooking(@PathVariable(value = "id") Long id) {
        BookingDTO bookingDTO = adminService.removeBooking(id);
        return new Response<>(true, bookingDTO, "Successful remove");
    }

}
