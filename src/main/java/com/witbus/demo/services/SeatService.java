package com.witbus.demo.services;

import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.SeatDTO;

import java.util.List;

public interface SeatService {
    List<SeatDTO> listSeat(Long id);

 //   List<BusDTO> listSeatBus(Long id);
}
