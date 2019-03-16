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
        booking.setDate(bookingDTO.getDate());
//        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser().getId());
//        User user;
//        if (!userOptional.isPresent()) {
//            return;
//        } else {
//            User editUser = userOptional.get();
//            user = userRepository.save(editUser);
//        }
        Optional<Seat> seatOptional = seatRepository.findById(bookingDTO.getSeat().getId());
        Seat seat;
        if (seatOptional.isPresent()) {
            Seat editSeat = seatOptional.get();
            editSeat.setStatus(true);
            seat = seatRepository.save(editSeat);
        } else {
            return;
        }
//        booking.setUser(user);
        booking.setSeat(seat);
        Booking saveBooking = bookingRepository.save(booking);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo(saveBooking.getEmail());
            helper.setText("\nChào: " + saveBooking.getName() +
                            "\nSố điện thoại của bạn: " + saveBooking.getPhone() +
                            "\nEmail: " + saveBooking.getEmail() +
                            "\nDịch vụ xe bạn chọn: "+saveBooking.getSeat().getBus().getBusOwner().getName()+
                            "\nMã loại xe: "+saveBooking.getSeat().getBus().getName()+
                            "\nTuyến: "+saveBooking.getSeat().getBus().getOrigin()+
                            " ---> "+saveBooking.getSeat().getBus().getDestination()+
                            "\nNgày đặt xe: "+saveBooking.getSeat().getBus().getDate()+
                            "\nThời gian: "+saveBooking.getSeat().getBus().getStartTime()+
                            "\nGhế: " + saveBooking.getSeat().getName() +
                            "\nGiá: " + saveBooking.getPrice() +
                            "\nMã code: " + saveBooking.getNumber() +
                            "\n-------------------------------------------" +
                            "\nCẢM ƠN BẠN ĐÃ SỬ DỤNG ỨNG DỤNG CỦA CHÚNG TÔI"
            );
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

    @Override
    public void checkEmail(BookingDTO bookingDTO) {
        int min = 1;
        int max = 9999;
        Random rd = new Random();
        int number = min + rd.nextInt(max - min);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo(bookingDTO.getEmail());
            helper.setText("\nMã xác nhận của bạn là  " + number +
                    " CẢM ƠN BẠN ĐÃ SỬ DỤNG ỨNG DỤNG CỦA CHÚNG TÔI"
            );
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }
}
