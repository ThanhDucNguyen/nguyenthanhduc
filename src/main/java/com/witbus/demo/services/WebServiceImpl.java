package com.witbus.demo.services;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dao.models.BusOwner;
import com.witbus.demo.dao.models.User;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;
import com.witbus.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebServiceImpl implements WebService{

    private AdminRepository adminRepository;
    private BusRepository busRepository;
    private BusOwnerRepository busOwnerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private OfferRepository offerRepository;

    public WebServiceImpl(AdminRepository adminRepository, BusRepository busRepository, BusOwnerRepository busOwnerRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository, OfferRepository offerRepository) {

        this.adminRepository = adminRepository;
        this.busRepository = busRepository;
        this.busOwnerRepository = busOwnerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public BusOwnerDTO loginManager(BusOwnerDTO busOwnerDTO) {
        BusOwner busOwner = busOwnerRepository.findByManager(busOwnerDTO.getEmail(),busOwnerDTO.getPassword());
        if (busOwner != null){
            busOwnerDTO.setId(busOwner.getId());
            busOwnerDTO.setName(busOwner.getName());
        }
        return busOwnerDTO;
    }

    @Override
    public List<BusDTO> listXe(Long id) {
        List<BusDTO> busDTOS = new ArrayList<>();
        List<Bus> buses = busRepository.listXe(id);
        for (Bus bus:buses){
            BusDTO busDTO = new BusDTO();
            busDTO.setId(bus.getId());
            busDTO.setName(bus.getName());
            busDTO.setOrigin(bus.getOrigin());
            busDTO.setDestination(bus.getDestination());

            busDTOS.add(busDTO);
        }
        return busDTOS;
    }
}
