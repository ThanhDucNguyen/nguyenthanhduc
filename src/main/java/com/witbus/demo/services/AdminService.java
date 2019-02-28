package com.witbus.demo.services;

import com.witbus.demo.dao.models.*;
import com.witbus.demo.dto.*;

import java.util.List;

public interface AdminService {
    UserDTO login(UserDTO userDTO);

    //------------------------------BusOwner---------------------------------///
    List<BusOwnerDTO> listBusOwner();
    BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO);
    BusOwnerDTO removeBusOwner(Long id);
    BusOwnerDTO updateBusOwner(BusOwnerDTO busOwnerDTO );
    BusOwner detailBusOwner( Long id);

    //------------------------------Bus---------------------------------///
    List<BusDTO> listBus();
    BusDTO addBus(BusDTO busDTO);
    BusDTO removeBus(Long id);
    BusDTO updateBus( BusDTO busDTO);
    Bus detailBus(Long id);

    //------------------------------Seat---------------------------------///
    List<SeatDTO> listSeat();
    SeatDTO addSeat(SeatDTO seatDTO);
    SeatDTO removeSeat(Long id);
    SeatDTO updateSeat(SeatDTO seatDTO);
    Seat detailSeat(Long id);

    //------------------------------User---------------------------------///
    List<UserDTO> listUser();
    UserDTO addUser(UserDTO userDTO);
    UserDTO removeUser(Long id);
    UserDTO updateUser(UserDTO userDTO);
    User detailUser(Long id);

    //------------------------------Offer---------------------------------///

    List<OfferDTO> listOffer();
    OfferDTO addOffer(OfferDTO offerDTO);
    OfferDTO removeOffer(Long id);
    OfferDTO updateOffer(OfferDTO offerDTO);
    Offer detailOffer(Long id);
    //------------------------------Booking---------------------------------///

    List<BookingDTO> listBooking();
    BookingDTO removeBooking(Long id);
    List<BookingDTO> bookingListId(Long id);
    List<BookingDTO> bookingListBySeatId(Long id);
    BookingDTO addBooking(BookingDTO bookingDTO);
    Booking detailBooking(Long id);
    BookingDTO updateBooking(BookingDTO bookingDTO);
    Object sendHelp(HelpDTO helpDTO);

}
