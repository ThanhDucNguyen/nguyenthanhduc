package com.witbus.demo.controllers;

import com.witbus.demo.dto.Utils.Response;
import com.witbus.demo.dto.BookingDTO;
import com.witbus.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class BookingController {
    @Autowired
    private JavaMailSender sender;
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/booking")
    public @ResponseBody
    Response addBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.booking(bookingDTO);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo("ntduc2812@gmail.com");
            helper.setText("\nHi: " + bookingDTO.getName() +
                            "\nPhone: " + bookingDTO.getPhone() +
                            "\nEmail: " + bookingDTO.getEmail() +
//                            "Bus Owner: "+bookingDTO.getSeat().getBus().getBusOwner().getName()
//                    "\nBus Name: "+bookingDTO.getSeat().getBus().getName()+
//                    "\nOrigin: "+bookingDTO.getSeat().getBus().getName()+" ---> Destination"+bookingDTO.getSeat().getBus().getDestination()+
//                    "\nDate: "+bookingDTO.getSeat().getBus().getDate()+
//                    "\nStart time: "+bookingDTO.getSeat().getBus().getStartTime()+
                            "\nSeat: " + bookingDTO.getSeat().getName() +
                            "\nPrice: " + bookingDTO.getSeat().getPrice() +
                            "\nPay: " + bookingDTO.getPay() +
                            "\nCode number booking: " + bookingDTO.getNumber() +
                            "\n-------------------------------------------" +
                            "\nTHANK YOU FOR READING THE LETTER"
            );
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
        Response<BookingDTO> response = new Response<>(true, bookingDTO, "Successful!");
        return response;
    }

}
