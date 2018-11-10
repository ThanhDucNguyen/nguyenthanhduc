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
    private BusOwnerRepository busOwnerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    @Autowired
    private OfferRepository offerRepository;

    public AdminServiceImpl(BusOwnerRepository bus_ownerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository ){
        this.busRepository = busRepository;
        this.busOwnerRepository = bus_ownerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }
    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = adminRepository.findByAdmin(userDTO.getName(),userDTO.getPassword());
        if (user != null){
            userDTO.setId(user.getId());
        }
        return userDTO;

    }

    @Override
    public List<BusOwnerDTO> listBusOwner() {
        List<BusOwnerDTO> busOwnerDTOS = new ArrayList<>();
        List<BusOwner> busOwners = busOwnerRepository.findAll();
        for (BusOwner busOwner : busOwners) {
            BusOwnerDTO busOwnerDTO = new BusOwnerDTO();
            busOwnerDTO.setId(busOwner.getId());
            busOwnerDTO.setName(busOwner.getName());

            busOwnerDTOS.add(busOwnerDTO);
        }

        return busOwnerDTOS;
    }
    @Override
    public BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO) {
        BusOwner busOwner = new BusOwner();
        busOwner.setName(busOwnerDTO.getName());
        busOwnerRepository.save(busOwner);
        return null;
    }

    @Override
    public BusOwnerDTO removeBusOwner(Long id) {
        busOwnerRepository.deleteById(id);
        return null;
    }

    @Override
    public BusOwnerDTO updateBusOwner(Long id, BusOwnerDTO busOwnerDTO) {
        Optional<BusOwner> busOwnerOptional = busOwnerRepository.findById(id);
        if( busOwnerOptional.isPresent()){
            BusOwner busOwner =busOwnerOptional.get();
            busOwner.setName(busOwnerDTO.getName());

            busOwnerRepository.save(busOwner);
        }
        return  busOwnerDTO;
    }

    @Override
    public BusOwnerDTO detailBusOwner( Long id) {
        BusOwnerDTO busOwnerDTO = new BusOwnerDTO();
        Optional<BusOwner> busOwnerOptional = busOwnerRepository.findById(id);
        if( busOwnerOptional.isPresent()){
            BusOwner busOwner =busOwnerOptional.get();
            busOwnerDTO .setId(id);
            busOwnerDTO.setName(busOwner.getName());


        }
        return  busOwnerDTO;


    }



    //------------------------------------Bus--------------------------------------------//
    @Override
    public List<BusDTO> listBus() {
        List<BusDTO> busDTOS = new ArrayList<>();
        List<Bus> buses = busRepository.findAll();
        for (Bus bus : buses) {
            BusDTO busDTO = new BusDTO();
            busDTO.setId(bus.getId());
            busDTO.setPlate(bus.getPlate());
            busDTO.setName(bus.getName());
            busDTO.setOrigin(bus.getOrigin());
            busDTO.setDestination(bus.getDestination());
            busDTO.setStartTime(bus.getStartTime());
            busDTO.setEndTime(bus.getEndTime());
            busDTO.setDistanceTime(bus.getDistanceTime());
            busDTO.setDate(bus.getDate());
            busDTO.setPriceDefault(bus.getPriceDefault());

            BusOwnerDTO busOwnerDTO = new BusOwnerDTO();
            busOwnerDTO.setId(bus.getBusOwner().getId());
            busOwnerDTO.setName(bus.getBusOwner().getName());
            busDTO.setBusOwner(busOwnerDTO);

            busDTOS.add(busDTO);
        }
        return busDTOS;
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
        Optional<BusOwner> busOwnerOptional = busOwnerRepository.findById(busDTO.getBusOwner().getId());
        if (busOwnerOptional.isPresent()) {
            bus.setBusOwner(busOwnerOptional.get());
            busRepository.save(bus);
        }

        return null;
    }

    @Override
    public BusDTO removeBus(Long id) {
        busRepository.deleteById(id);
        return null;
    }

    @Override
    public BusDTO updateBus(Long id, BusDTO busDTO) {
        Optional<Bus> busOptional = busRepository.findById(id);

        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();
            bus.setName(busDTO.getName());
            bus.setOrigin(busDTO.getOrigin());
            bus.setDestination(busDTO.getDestination());
            bus.setStartTime(busDTO.getStartTime());
            bus.setEndTime(busDTO.getEndTime());
            bus.setDistanceTime(busDTO.getDistanceTime());
            bus.setDate(busDTO.getDate());
            bus.setPriceDefault(busDTO.getPriceDefault());

            if (!bus.getBusOwner().getId().equals(busDTO.getBusOwner().getId())) {
                Optional<BusOwner> busOwnerOptional = busOwnerRepository.findById(busDTO.getBusOwner().getId());
                if (busOwnerOptional.isPresent()) {
                    bus.setBusOwner(busOwnerOptional.get());
                }
            }
            busRepository.save(bus);
        }
        return  null;
    }
    @Override
    public List<BusDTO> busListId(Long id) {
        List<BusDTO> busDTOS = new ArrayList<>();
        List<Bus> buses = busRepository.listById(id);
        for (Bus bus : buses) {
            BusDTO busDTO = new BusDTO();
            busDTO.setId(bus.getId());
            busDTO.setPlate(bus.getPlate());
            busDTO.setName(bus.getName());
            busDTO.setOrigin(bus.getOrigin());
            busDTO.setDestination(bus.getDestination());
            busDTO.setStartTime(bus.getStartTime());
            busDTO.setEndTime(bus.getEndTime());
            busDTO.setDistanceTime(bus.getDistanceTime());
            busDTO.setDate(bus.getDate());
            busDTO.setPriceDefault(bus.getPriceDefault());

            BusOwnerDTO busOwnerDTO = new BusOwnerDTO();
            busOwnerDTO.setId(bus.getBusOwner().getId());
            busOwnerDTO.setName(bus.getBusOwner().getName());
            busDTO.setBusOwner(busOwnerDTO);

            busDTOS.add(busDTO);
        }
        return busDTOS;
    }



    //----------------------------------Seat-------------------------------------------///
    @Override
    public List<SeatDTO> listSeat() {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        List<Seat> seats = seatRepository.findAll();
        for (Seat seat: seats){
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(seat.getId());
            seatDTO.setName(seat.getName());
            seatDTO.setStatus(seat.getStatus());
            seatDTO.setSeatType(seat.getSeatType());
            seatDTO.setPrice(seat.getPrice());


            BusDTO busDTO = new BusDTO();
            busDTO.setId(seat.getBus().getId());
            busDTO.setPlate(seat.getBus().getPlate());
            busDTO.setName(seat.getBus().getName());
            busDTO.setOrigin(seat.getBus().getOrigin());
            busDTO.setDestination(seat.getBus().getDestination());
            busDTO.setStartTime(seat.getBus().getStartTime());
            busDTO.setEndTime(seat.getBus().getEndTime());
            busDTO.setDistanceTime(seat.getBus().getDistanceTime());
            busDTO.setDate(seat.getBus().getDate());
            busDTO.setPriceDefault(seat.getBus().getPriceDefault());



            seatDTO.setBus(busDTO);



            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }

    @Override
    public SeatDTO addSeat(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setName(seatDTO.getName());
        seat.setStatus(seatDTO.getStatus());
        seat.setSeatType(seatDTO.getSeatType());
        seat.setPrice(seatDTO.getPrice());
        Optional<Bus> busOptional = busRepository.findById(seatDTO.getBus().getId());
        if (busOptional.isPresent()) {
            seat.setBus(busOptional.get());
            seatRepository.save(seat);
        }

        return null;
    }

    @Override
    public SeatDTO removeSeat(Long id) {
        seatRepository.deleteById(id);
        return null;
    }

    @Override
    public SeatDTO updateSeat(Long id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (seatOptional.isPresent()) {
            SeatDTO seatDTO = new SeatDTO() ;
            Seat seat = seatOptional.get();
            seat.setName(seatDTO.getName());
            seat.setStatus(seatDTO.getStatus());
            seat.setPrice(seatDTO.getPrice());
            seat.setSeatType(seatDTO.getSeatType());


            if (!seat.getBus().getId().equals(seatDTO.getBus().getId())) {
                Optional<Bus> busOptional = busRepository.findById(seatDTO.getBus().getId());
                if (busOptional.isPresent()) {
                    seat.setBus(busOptional.get());
                }
            }
            seatRepository.save(seat);
        }
        return  null ;
    }

    @Override
    public List<SeatDTO> seatListId(Long id) {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        List<Seat> seats = seatRepository.listByBusId(id);
        for (Seat seat: seats){
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(seat.getId());
            seatDTO.setName(seat.getName());
            seatDTO.setStatus(seat.getStatus());
            seatDTO.setSeatType(seat.getSeatType());
            seatDTO.setPrice(seat.getPrice());


            BusDTO busDTO = new BusDTO();
            busDTO.setId(seat.getBus().getId());
            busDTO.setPlate(seat.getBus().getPlate());
            busDTO.setName(seat.getBus().getName());
            busDTO.setOrigin(seat.getBus().getOrigin());
            busDTO.setDestination(seat.getBus().getDestination());
            busDTO.setStartTime(seat.getBus().getStartTime());
            busDTO.setEndTime(seat.getBus().getEndTime());
            busDTO.setDistanceTime(seat.getBus().getDistanceTime());
            busDTO.setDate(seat.getBus().getDate());
            busDTO.setPriceDefault(seat.getBus().getPriceDefault());



            seatDTO.setBus(busDTO);



            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }

    //---------------------------------------User---------------------------------------------------//

    @Override
    public List<UserDTO> listUser() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setRole(user.getRole());

            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {

        User user1 = new User();
        user1.setName(userDTO.getName());
        user1.setPhone(userDTO.getPhone());
        user1.setPassword(userDTO.getPassword());
        user1.setEmail(userDTO.getEmail());
        user1.setRole(userDTO.getRole());
        userRepository.save(user1);



        return null;
    }

    @Override
    public UserDTO removeUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDTO.getName());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            userRepository.save(user);
        }
    }
    //---------------------------------------Offer---------------------------------------------------//

    @Override
    public List<OfferDTO> listOffer() {
        List<OfferDTO> offerDTOS2 = new ArrayList<>();
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer:offers){
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setId(offer.getId());
            offerDTO.setName(offer.getName());
            offerDTO.setPrice(offer.getPrice());
            offerDTO.setInfo(offer.getInfo());
            offerDTO.setCode(offer.getCode());

            offerDTOS2.add(offerDTO);
        }
        return offerDTOS2;
    }

    @Override
    public OfferDTO addOffer(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setName(offerDTO.getName());
        offer.setInfo(offerDTO.getInfo());
        offer.setCode(offerDTO.getCode());
        offer.setPrice(offerDTO.getPrice());
        offerRepository.save(offer);
        return null;
    }

    @Override
    public OfferDTO removeOffer(Long id) {
        offerRepository.deleteById(id);
        return null;
    }

    @Override
    public OfferDTO updateOffer(Long id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if( offerOptional.isPresent()){
            Offer offer =offerOptional.get();
            OfferDTO offerDTO = new OfferDTO();
            offer.setCode(offerDTO.getCode());

            offer.setInfo(offerDTO.getInfo());

            offer.setName(offerDTO.getName());

            offer.setPrice(offerDTO.getPrice());


            offerRepository.save(offer);
        }
        return  null;
    }
    //---------Booking -----------------------//


    @Override
    public List<BookingDTO> listBooking() {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setPay(booking.getPay());
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setNumber(booking.getNumber());
            bookingDTO.setName(booking.getName());
            bookingDTO.setPhone(booking.getPhone());
            bookingDTO.setEmail(booking.getEmail());


            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(booking.getSeat().getId());
            bookingDTO.setSeat(seatDTO);

            UserDTO userDTO = new UserDTO();
            userDTO.setId(booking.getUser().getId());
            bookingDTO.setUser(userDTO);

            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }

    @Override
    public BookingDTO removeBooking(Long id) {
        bookingRepository.deleteById(id);
        return null;
    }

    @Override
    public List<BookingDTO> bookingListId(Long id) {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        List<Booking> bookings = bookingRepository.listByUserId(id);
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setPay(booking.getPay());
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setNumber(booking.getNumber());
            bookingDTO.setName(booking.getName());
            bookingDTO.setPhone(booking.getPhone());
            bookingDTO.setEmail(booking.getEmail());


            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(booking.getSeat().getId());
            bookingDTO.setSeat(seatDTO);

            UserDTO userDTO = new UserDTO();
            userDTO.setId(booking.getUser().getId());
            bookingDTO.setUser(userDTO);

            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }

    @Override
    public List<BookingDTO> bookingListBySeatId(Long id) {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        List<Booking> bookings = bookingRepository.listBySeatId(id);
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setPay(booking.getPay());
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setNumber(booking.getNumber());
            bookingDTO.setName(booking.getName());
            bookingDTO.setPhone(booking.getPhone());
            bookingDTO.setEmail(booking.getEmail());


            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(booking.getSeat().getId());
            bookingDTO.setSeat(seatDTO);

            UserDTO userDTO = new UserDTO();
            userDTO.setId(booking.getUser().getId());
            bookingDTO.setUser(userDTO);

            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }
}
