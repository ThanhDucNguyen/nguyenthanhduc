package com.witbus.demo.services;

import com.witbus.demo.dao.models.*;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    private BusRepository busRepository;
    private BusOwnerRepository busownerRepository;
    private SeatRepository seatRepository;
    private OfferRepository offerRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    public AdminServiceImpl(BusOwnerRepository busownerRepository, OfferRepository offerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        this.busRepository = busRepository;
        this.busownerRepository = busownerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = adminRepository.findByUserNameAndPassword(userDTO.getName(), userDTO.getPassword(), userDTO.getRole());
        if (user != null) {
            userDTO.setId(user.getId());
        }
        return userDTO;

    }

    @Override
    public List<BusOwnerDTO> listBusOwner() {
        List<BusOwnerDTO> busOwnerDTOS = new ArrayList<>();
        List<BusOwner> busOwners = busownerRepository.findAll();
        for (BusOwner busOwner : busOwners) {
            BusOwnerDTO busOwnerDTO = new BusOwnerDTO();
            busOwnerDTO.setId(busOwner.getId());//ckeck bus này
            busOwnerDTO.setName(busOwner.getName());

            busOwnerDTOS.add(busOwnerDTO);
        }

        return busOwnerDTOS;
    }

    @Override
    public List<BusDTO> listBus() {
        List<BusDTO> bookingDTOS = new ArrayList<>();
        List<Bus> bookings = busRepository.findAll();
        for (Bus bus : bookings) {
            BusDTO busDTO = new BusDTO();
            busDTO.setId(bus.getId());
            busDTO.setName(bus.getName());
            busDTO.setOrigin(bus.getOrigin());
            busDTO.setDestination(bus.getDestination());
            busDTO.setStartTime(bus.getStartTime());
            busDTO.setPriceDefault(bus.getPriceDefault());

            bookingDTOS.add(busDTO);
        }
        return bookingDTOS;
    }


    @Override
    public List<SeatDTO> listSeat() {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        List<Seat> seats = seatRepository.findAll();
        for (Seat seat : seats) {
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(seat.getId());
            seatDTO.setName(seat.getName());
            seatDTO.setStatus(seat.getStatus());
            seatDTO.setSeatType(seat.getSeatType());
            seatDTO.setPrice(seat.getPrice());

            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }

    @Override
    public List<BookingDTO> listBooking() {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setName(booking.getName());
            bookingDTO.setEmail(booking.getEmail());
            bookingDTO.setPhone(booking.getPhone());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setPay(booking.getPay());
            bookingDTO.setNumber(booking.getNumber());

            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }

    @Override
    public List<OfferDTO> listOffer() {
        List<OfferDTO> offerDTOS = new ArrayList<>();
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setId(offer.getId());
            offerDTO.setName(offer.getName());
            offerDTO.setCode(offer.getCode());
            offerDTO.setInfo(offer.getInfo());
            offerDTO.setPrice(offer.getPrice());

            offerDTOS.add(offerDTO);
        }
        return offerDTOS;
    }
    //===========================================================ADD===========================================================//

    @Override
    public BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO) {
        BusOwner busOwner = new BusOwner();
        busOwner.setName(busOwnerDTO.getName());
        busownerRepository.save(busOwner);
        return null;
    }

    @Override
    public BusDTO addBus(BusDTO busDTO) {

        Bus bus = new Bus();
        bus.setPlate(busDTO.getPlate());
        bus.setName(busDTO.getName());
        bus.setOrigin(busDTO.getOrigin());
        bus.setDestination(busDTO.getDestination());
        bus.setStartTime(busDTO.getStartTime());
        bus.setEndTime(busDTO.getEndTime());
        bus.setDistanceTime(busDTO.getDistanceTime());
        bus.setDate(busDTO.getDate());
        bus.setPriceDefault(busDTO.getPriceDefault());
        Optional<BusOwner> busOwnerOptional = busownerRepository.findById(busDTO.getBusOwner().getId());
        if (busOwnerOptional.isPresent()) {
            bus.setBusOwner(busOwnerOptional.get());
            busRepository.save(bus);
        }

        return null;
    }

    @Override
    public SeatDTO addSeat(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setName(seatDTO.getName());
        seat.setStatus(seatDTO.getStatus());
        seat.setPrice(seatDTO.getPrice());
        seat.setSeatType(seatDTO.getSeatType());
        Optional<Bus> busOptional = busRepository.findById(seatDTO.getBus().getId());
        if (busOptional.isPresent()) {
            seat.setBus(busOptional.get());
            seatRepository.save(seat);
        }

        return null;
    }

    @Override
    public OfferDTO addOffer(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setName(offerDTO.getName());
        offer.setCode(offerDTO.getCode());
        offer.setInfo(offerDTO.getInfo());
        offer.setPrice(offerDTO.getPrice());
        offerRepository.save(offer);
        return null;
    }

    @Override
    public BusOwnerDTO removeBusOwner(Long id) {
        busownerRepository.deleteById(id);
        return null;
    }
}
