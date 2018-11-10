package com.witbus.demo.services;

import com.witbus.demo.dao.models.BusOwner;
import com.witbus.demo.dto.*;

import java.util.List;

public interface AdminService {
    UserDTO login(UserDTO userDTO);

    //------------------------------BusOwner---------------------------------///
    List<BusOwnerDTO> listBusOwner();
    BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO);
    BusOwnerDTO removeBusOwner(Long id);
    BusOwnerDTO updateBusOwner(Long id,BusOwnerDTO busOwnerDTO );
    BusOwnerDTO detailBusOwner( Long id);


    //------------------------------Bus---------------------------------///
    List<BusDTO> listBus();
    BusDTO addBus(BusDTO busDTO);
    BusDTO removeBus(Long id);
    BusDTO updateBus(Long id, BusDTO busDTO);
    List<BusDTO> busListId(Long id);

    //------------------------------Seat---------------------------------///
    List<SeatDTO> listSeat();
    SeatDTO addSeat(SeatDTO seatDTO);
    SeatDTO removeSeat(Long id);
    SeatDTO updateSeat(Long id);
    List<SeatDTO> seatListId(Long id);

    //------------------------------User---------------------------------///
    List<UserDTO> listUser();
    UserDTO addUser(UserDTO userDTO);
    UserDTO removeUser(Long id);
    void updateUser(UserDTO userDTO);


    //------------------------------Offer---------------------------------///

    List<OfferDTO> listOffer();
    OfferDTO addOffer(OfferDTO offerDTO);
    OfferDTO removeOffer(Long id);
    OfferDTO updateOffer(Long id);
    //------------------------------Booking---------------------------------///

    List<BookingDTO> listBooking();
    BookingDTO removeBooking(Long id);
    List<BookingDTO> bookingListId(Long id);
    List<BookingDTO> bookingListBySeatId(Long id);


}
