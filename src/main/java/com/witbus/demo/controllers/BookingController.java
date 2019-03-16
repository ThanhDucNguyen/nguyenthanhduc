package com.witbus.demo.controllers;

import com.witbus.demo.dto.Response;
import com.witbus.demo.dto.BookingDTO;
import com.witbus.demo.services.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/booking")
    public Response<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.booking(bookingDTO);
        return new Response<>(true, bookingDTO, "Successful!");
    }
    @PostMapping(value = "/checkEmail")
    public Response<BookingDTO> checkEmail(@RequestBody BookingDTO bookingDTO) {
        bookingService.checkEmail(bookingDTO);
        return new Response<>(true, bookingDTO, "Successful!");
    }

}
