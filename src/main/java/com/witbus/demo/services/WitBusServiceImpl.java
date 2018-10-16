package com.witbus.demo.services;

import com.witbus.demo.dao.models.*;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WitBusServiceImpl implements WitBusService {
    @Autowired
    private BusRepository busRepository;
    private BusOwnerRepository bus_ownerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    public WitBusServiceImpl(BusOwnerRepository bus_ownerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository ){
        this.busRepository = busRepository;
        this.bus_ownerRepository = bus_ownerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    //========================================================== BOOKING ====================================================//

    //=======================================================================================================================================//

    @Override
    public void booking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setPay(bookingDTO.getPay());
        booking.setPrice(bookingDTO.getPrice());
        booking.getNumber();
        booking.setTickets(bookingDTO.getTickets());
        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser().getId());
        User user ;
        if (!userOptional.isPresent()){
            User newUser = new User();
            newUser.setId(bookingDTO.getUser().getId());
            newUser.setName(bookingDTO.getUser().getName());
            newUser.setPhone(bookingDTO.getUser().getPhone());
            newUser.setEmail(bookingDTO.getUser().getEmail());
            user = userRepository.save(newUser);
        }
        else {
            User editUser = userOptional.get();
            editUser.setName(bookingDTO.getUser().getName());
            editUser.setPhone(bookingDTO.getUser().getPhone());
            editUser.setEmail(bookingDTO.getUser().getEmail());
            user = userRepository.save(editUser);
        }
        Optional<Seat> seatOptional = seatRepository.findById(bookingDTO.getSeat().getId());
        Seat seat;
        if (seatOptional.isPresent()){
            Seat editSeat = seatOptional.get();
            editSeat.setStatus(bookingDTO.getSeat().getStatus());
            seat =seatRepository.save(editSeat);
        }else {
            return;
        }
        booking.setUser(user);
        booking.setSeat(seat);
        Booking saveBooking = bookingRepository.save(booking);
    }

//    @Override
//    public List<BookingDTO> getUser() {
//        List<BookingDTO> bookingDTOS = new ArrayList<>();
//        List<Booking> bookings = bookingRepository.findAll();
//        for (Booking booking : bookings) {
//            BookingDTO bookingDTO = new BookingDTO();
//            bookingDTO.setId(booking.getId());
//            bookingDTO.setPay(booking.getPay());
//            bookingDTO.setPrice(booking.getPrice());
//            bookingDTO.setTickets(booking.getTickets());
//
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(booking.getUser().getId());
//            userDTO.setName(booking.getUser().getName());
//            userDTO.setEmail(booking.getUser().getEmail());
//            userDTO.setPhone(booking.getUser().getPhone());
//
////            SeatDTO seatDTO = new SeatDTO();
////            seatDTO.setName(booking.getSeat().getName());
////            seatDTO.setStatus(booking.getSeat().getStatus());
////            seatDTO.setPrice(booking.getSeat().getPrice());
//
//
//            bookingDTO.setUser(userDTO);
//            //bookingDTO.setSeat(seatDTO);
//
//            bookingDTOS.add(bookingDTO);
//        }
//
//        return bookingDTOS;
//    }

}
