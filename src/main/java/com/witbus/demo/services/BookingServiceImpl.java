package com.witbus.demo.services;

import com.witbus.demo.dao.models.*;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BusRepository busRepository;
    private BusOwnerRepository bus_ownerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    @Autowired
    private JavaMailSender sender;

    public BookingServiceImpl(BusOwnerRepository bus_ownerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository) {
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
        booking.setNumber(bookingDTO.getNumber());
        booking.setName(bookingDTO.getName());
        booking.setPhone(bookingDTO.getPhone());
        booking.setEmail(bookingDTO.getEmail());
        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser().getId());
        User user;
        if (!userOptional.isPresent()) {
            return;
        } else {
            User editUser = userOptional.get();
            user = userRepository.save(editUser);
        }
        Optional<Seat> seatOptional = seatRepository.findById(bookingDTO.getSeat().getId());
        Seat seat;
        if (seatOptional.isPresent()) {
            Seat editSeat = seatOptional.get();
            editSeat.setStatus(bookingDTO.getSeat().getStatus());
            seat = seatRepository.save(editSeat);
        } else {
            return;
        }
        booking.setUser(user);
        booking.setSeat(seat);
        Booking saveBooking = bookingRepository.save(booking);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo(saveBooking.getEmail());
            helper.setText("\nHi: " + saveBooking.getName() +
                            "\nPhone: " + saveBooking.getPhone() +
                            "\nEmail: " + saveBooking.getEmail() +
                            "\nBus Owner: "+saveBooking.getSeat().getBus().getBusOwner().getName()+
                            "\nBus Name: "+saveBooking.getSeat().getBus().getName()+
                            "\nOrigin: "+saveBooking.getSeat().getBus().getOrigin()+
                            " ---> Destination: "+saveBooking.getSeat().getBus().getDestination()+
                            "\nDate: "+saveBooking.getSeat().getBus().getDate()+
                            "\nStart time: "+saveBooking.getSeat().getBus().getStartTime()+
                            "\nSeat: " + saveBooking.getSeat().getName() +
                            "\nPrice: " + saveBooking.getSeat().getPrice() +
                            "\nPay: " + saveBooking.getPay() +
                            "\nCode number booking: " + saveBooking.getNumber() +
                            "\n-------------------------------------------" +
                            "\nTHANK YOU FOR READING THE LETTER"
            );
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }
}
