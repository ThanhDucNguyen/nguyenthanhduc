package com.witbus.demo.services;

import com.witbus.demo.dto.*;

import java.util.List;

public interface AdminService {
    UserDTO login(UserDTO userDTO);

    List<BusDTO> listBus();

    List<BusOwnerDTO> listBusOwner();

    List<SeatDTO> listSeat();

    BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO);

    BusDTO addBus(BusDTO busDTO);

    List<OfferDTO> listOffer();

    OfferDTO addOffer(OfferDTO offerDTO);

    BusOwnerDTO removeBusOwner(Long id);
}
