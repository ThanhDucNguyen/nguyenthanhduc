package com.witbus.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.witbus.demo.dao.models.*;
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
            mav.setViewName("redirect:/login");
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
            mav.setViewName("redirect:/login");
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
            mav.setViewName("redirect:/login");
        } else {
            mav.addObject("busOwner", adminService.listBusOwner());
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
            mav.setViewName("redirect:/login");
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
            mav.addObject("busOwner", adminService.listBusOwner());
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
            mav.setViewName("redirect:/login");
        } else {
            mav.addObject("bus", adminService.listBus());
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
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Seat seat = adminService.detailSeat(id);
        mav.addObject("bus", adminService.listBus());
        mav.addObject("detail",seat);

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
            mav.addObject("seat",seat);
            mav.addObject("bus", adminService.listBus());
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

    //------------------------------Users---------------------------------///
    @GetMapping(value = "/users")
    public ModelAndView users(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        mav.addObject("user", adminService.listUser());

        mav.setViewName("users");
        return mav;
    }
    @GetMapping(value = "/addUser")
    public ModelAndView addUser(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null) {
            mav.setViewName("redirect:/login");
        } else {
            mav.addObject("user", new UserDTO());
            mav.setViewName("addUser");
        }

        return mav;
    }
    @PostMapping(value = "/usersProcess")
    public ModelAndView addUsersProcess(UserDTO userDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("user",adminService.addUser(userDTO));
        mav.setViewName("redirect:/user");
        return mav;
    }
    @GetMapping(value = "/removeUser/{id}/delete")
    public ModelAndView removeUser(@PathVariable(value = "id") Long id, SeatDTO seatDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user",adminService.removeUser(id));
        mav.setViewName("redirect:/users");
        return mav;
    }
    @GetMapping(value = "/admin-detailUser-{id}")
    public ModelAndView detailUser(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        User user = adminService.detailUser(id);
        mav.addObject("detail",user);

        mav.setViewName("detailUser");
        return mav;
    }
    @GetMapping(value = "/admin-updateUser-{id}")
    public ModelAndView updateUser(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        User user = adminService.detailUser(id);
        if(user != null){
            mav.addObject("user",user);
        }

        mav.setViewName("editUser");
        return mav;
    }

    @PostMapping(value = "/admin-updateUser-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateUserProcess( UserDTO userDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateUser(userDTO);
        mav.setViewName("redirect:/users");
        return mav;
    }

    //------------------------------Offer---------------------------------///
    @GetMapping(value = "/offers")
    public ModelAndView Offer(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        mav.addObject("offer", adminService.listOffer());

        mav.setViewName("offer");
        return mav;
    }
    @GetMapping(value = "/addOffer")
    public ModelAndView addOffer(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null) {
            mav.setViewName("redirect:/login");
        } else {
            mav.addObject("offer", new OfferDTO());
            mav.setViewName("addOffer");
        }

        return mav;
    }
    @PostMapping(value = "/offerProcess")
    public ModelAndView addOfferProcess(OfferDTO offerDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("offer",adminService.addOffer(offerDTO));
        mav.setViewName("redirect:/offers");
        return mav;
    }
    @GetMapping(value = "/removeOffer/{id}/delete")
    public ModelAndView removeOffer(@PathVariable(value = "id") Long id,  HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("user",adminService.removeOffer(id));
        mav.setViewName("redirect:/offers");
        return mav;
    }
    @GetMapping(value = "/admin-detailOffer-{id}")
    public ModelAndView detailOffer(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Offer offer = adminService.detailOffer(id);
        mav.addObject("detail",offer);

        mav.setViewName("detailOffer");
        return mav;
    }
    @GetMapping(value = "/admin-updateOffer-{id}")
    public ModelAndView updateOffer(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Offer offer = adminService.detailOffer(id);
        if(offer != null){
            mav.addObject("offer",offer);
        }

        mav.setViewName("editOffer");
        return mav;
    }

    @PostMapping(value = "/admin-updateOffer-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateOfferProcess( OfferDTO offerDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateOffer(offerDTO);
        mav.setViewName("redirect:/offers");
        return mav;
    }

    //------------------------------Booking---------------------------------///
    @GetMapping(value = "/booking")
    public ModelAndView Booking(HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("seats", adminService.listSeat());
        mav.addObject("booking", adminService.listBooking());

        mav.setViewName("booking");
        return mav;
    }
    @GetMapping(value = "/addBooking")
    public ModelAndView addBooking(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null) {
            mav.setViewName("redirect:/login");
        } else {
            mav.addObject("seats", adminService.listSeat());
            mav.addObject("user", adminService.listUser());
            mav.addObject("booking", new BookingDTO());
            mav.setViewName("addBooking");
        }

        return mav;
    }
    @PostMapping(value = "/bookingProcess")
    public ModelAndView addBookingProcess(BookingDTO bookingDTO, HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.addObject("booking",adminService.addBooking(bookingDTO));
        mav.setViewName("redirect:/booking");
        return mav;
    }

    @GetMapping(value = "/removeBooking/{id}/delete")
    public ModelAndView removeBooking(@PathVariable(value = "id") Long id,  HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("booking",adminService.removeBooking(id));
        mav.setViewName("redirect:/booking");
        return mav;
    }
    @GetMapping(value = "/admin-detailBooking-{id}")
    public ModelAndView detailBooking(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Booking booking = adminService.detailBooking(id);
        mav.addObject("seats", adminService.listSeat());
        mav.addObject("user", adminService.listUser());
        mav.addObject("detail",booking);

        mav.setViewName("detailBooking");
        return mav;
    }
    @GetMapping(value = "/admin-updateBooking-{id}")
    public ModelAndView updateBooking(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Booking booking = adminService.detailBooking(id);
        if(booking != null){
            mav.addObject("booking",booking);
        }

        mav.setViewName("editBooking");
        return mav;
    }

    @PostMapping(value = "/admin-updateBooking-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateBookingProcess( BookingDTO bookingDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateBooking(bookingDTO);
        mav.setViewName("redirect:/booking");
        return mav;
    }

}
