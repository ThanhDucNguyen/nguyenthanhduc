package com.witbus.demo.services;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dao.models.BusOwner;
import com.witbus.demo.dao.models.Seat;
import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.dto.SeatDTO;
import com.witbus.demo.dto.UserDTO;
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
    private BusOwnerRepository bus_ownerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    public AdminServiceImpl(BusOwnerRepository bus_ownerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository ){
        this.busRepository = busRepository;
        this.bus_ownerRepository = bus_ownerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }
    @Override
    public UserDTO login(UserDTO userDTO) {
            User user = adminRepository.findByUserNameAndPassword(userDTO.getName(),userDTO.getPassword(),userDTO.getRole());
            if (user != null){
                userDTO.setId(user.getId());
            }
            return userDTO;

    }

    @Override
    public List<BusOwnerDTO> listBusOwner() {
        List<BusOwnerDTO> busOwnerDTOS = new ArrayList<>();
        List<BusOwner> busOwners = bus_ownerRepository.findAll();
        for (BusOwner busOwner : busOwners) {
            BusOwnerDTO busOwnerDTO = new BusOwnerDTO();
            busOwnerDTO.setId(busOwner.getId());//ckeck bus này
            busOwnerDTO.setName(busOwner.getName());

            busOwnerDTOS.add(busOwnerDTO);
        }

        return busOwnerDTOS;
    }

    @Override
    public BusOwnerDTO delete(Long id) {
        //bus_ownerRepository.delete(id);
        return null;
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
    public BusDTO addBus(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setName(busDTO.getName());
        bus.setPlate(busDTO.getPlate());
        bus.setOrigin(busDTO.getOrigin());
        bus.setDestination(busDTO.getDestination());
        bus.setStartTime(busDTO.getStartTime());
        bus.setEndTime(busDTO.getEndTime());
        bus.setDistanceTime(busDTO.getDistanceTime());
        bus.setPriceDefault(busDTO.getPriceDefault());
        bus.setDate(busDTO.getDate());
        Optional<BusOwner> busOwnerOptional = bus_ownerRepository.findById(busDTO.getBusOwner().getId());
        BusOwner busOwner;
        if (busOwnerOptional.isPresent())
        {
            BusOwner editUser = busOwnerOptional.get();
            busOwner = bus_ownerRepository.save(editUser);
        }
        else {
            return null;
        }
        bus.setBusOwner(busOwner);
        busRepository.save(bus);
        return null;
    }

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

            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }

    @Override
    public BusOwnerDTO addBusOwner(BusOwnerDTO busOwnerDTO) {
        BusOwner busOwner = new BusOwner();
        busOwner.setName(busOwnerDTO.getName());
        bus_ownerRepository.save(busOwner);
        return null;
    }
}
