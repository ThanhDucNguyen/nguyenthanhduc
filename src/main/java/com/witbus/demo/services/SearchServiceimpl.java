package com.witbus.demo.services;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dao.models.Seat;
import com.witbus.demo.dao.repository.*;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusSeatDTO;
import com.witbus.demo.dto.TourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceimpl implements SearchService {
    @Autowired

    private BusRepository busRepository;
    private BusOwnerRepository bus_ownerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    public SearchServiceimpl(BusOwnerRepository bus_ownerRepository, BusRepository busRepository, SeatRepository seatRepository, UserRepository userRepository, BookingRepository bookingRepository ){
        this.busRepository = busRepository;
        this.bus_ownerRepository = bus_ownerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

//    @Override
//    public List<BusSeatDTO> searchTour(TourDTO tourDTO) {
//        String a = tourDTO.getLocationOrigin();
//        String b = tourDTO.getTicketType();
//        List<Seat> seats = seatRepository.getSeatAvailable(tourDTO.getLocationOrigin(),tourDTO.getTicketType());
//
//        //List<Seat> seats = seatRepository.getSeatAvailable(tourDTO.getLocationOrigin(),tourDTO.getTicketType());
//        List<BusSeatDTO> result = new ArrayList<>();
//        for (Seat seat :seats) {
//
//            //Kiem tra thu bus da ton tai chua
//            int index = -1 ;
//            for (int i = 0 ; i<result.size();i++) {
//                if(result.get(i).getBus().getId().equals(seat.getBus().getId())){
//                    index = i;
//
//                }
//                break;
//            }
//
//            //If index == -1 => add new ,index!=-1 => update (thêm xe vào)
//
//            if (index != -1 ) {
//                result.get(index).getSeats().add(seat.getId());
//
//                // }
//            }
//            else {
//                //if(seat.getBus().getOrigin().equals(tourDTO.getLocationOrigin())){
//                BusSeatDTO busSeatDTO = new BusSeatDTO();
//                BusDTO busDTO = new BusDTO();
//                busDTO.setId(seat.getBus().getId());//ckeck bus này
//                busDTO.setPlate(seat.getBus().getPlate());
//                busDTO.setName(seat.getBus().getName());
//                busDTO.setOrigin(seat.getBus().getOrigin());
//                busDTO.setDestination(seat.getBus().getDestination());
//                busDTO.setStartTime(seat.getBus().getStartTime());
//                busDTO.setEndTime(seat.getBus().getEndTime());
//                busDTO.setDistanceTime(seat.getBus().getDistanceTime());
//                busDTO.setPriceDefault(seat.getBus().getPriceDefault());
//
//                busSeatDTO.setBus(busDTO);
//
//
//                List<Long> listSeat = new ArrayList<>();
//                listSeat.add(seat.getId());
//                busSeatDTO.setSeats(listSeat);
//                result.add(busSeatDTO);
//
//            }
//
//
//        }
//        return result;
//    }

    @Override
    public List<BusDTO> searchTour(BusDTO busDTO) {
        List<Bus> bus = busRepository.getBus(busDTO.getOrigin(),busDTO.getDestination(),busDTO.getDate());
        List<BusDTO> result = new ArrayList<>();
        for (Bus bus1 :bus) {
            busDTO.setId(bus1.getId());//ckeck bus này
            busDTO.setPlate(bus1.getPlate());
            busDTO.setName(bus1.getName());
            busDTO.setOrigin(bus1.getOrigin());
            busDTO.setDestination(bus1.getDestination());
            busDTO.setStartTime(bus1.getStartTime());
            busDTO.setEndTime(bus1.getEndTime());
            busDTO.setDistanceTime(bus1.getDistanceTime());
            busDTO.setPriceDefault(bus1.getPriceDefault());

            result.add(busDTO);
        }
        return result;
    }

    @Override
    public List<BusDTO> listBus() {
        List<BusDTO> bookingDTOS = new ArrayList<>();
        List<Bus> bookings = busRepository.findAll();
        for (Bus bus : bookings) {
            BusDTO busDTO = new BusDTO();
            busDTO.setOrigin(bus.getOrigin());
            busDTO.setDestination(bus.getDestination());

            bookingDTOS.add(busDTO);
        }

        return bookingDTOS;
    }
}
