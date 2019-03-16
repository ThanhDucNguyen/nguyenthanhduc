package com.witbus.demo.services;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.dto.SeatDTO;

import java.util.List;

public interface WebService {
    BusOwnerDTO loginManager(BusOwnerDTO busOwnerDTO);

    List<BusDTO> listXe(Long id);

    List<SeatDTO> listGhe(Long id);

    BusDTO addBus(BusDTO busDTO);
}
