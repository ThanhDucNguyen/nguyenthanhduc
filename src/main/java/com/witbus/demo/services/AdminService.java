package com.witbus.demo.services;

import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.dto.SeatDTO;
import com.witbus.demo.dto.UserDTO;

import java.util.List;

public interface AdminService {
    UserDTO login(UserDTO userDTO);





    List<BusDTO> listBus();

    List<BusOwnerDTO> listBusOwner();

    List<SeatDTO> listSeat();

    BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO);

    BusOwnerDTO delete(Long id);
}
