package com.witbus.demo.services;

import com.witbus.demo.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    void booking(BookingDTO bookingDTO);
}
