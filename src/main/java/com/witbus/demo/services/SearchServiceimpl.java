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

    @Override
    public List<BusSeatDTO> searchTour(TourDTO tourDTO) {
        String a = tourDTO.getLocationOrigin();
        String b = tourDTO.getTicketType();
        List<Seat> seats = seatRepository.getSeatAvailable(tourDTO.getLocationOrigin(),tourDTO.getTicketType());

        //List<Seat> seats = seatRepository.getSeatAvailable(tourDTO.getLocationOrigin(),tourDTO.getTicketType());
        List<BusSeatDTO> result = new ArrayList<>();
        for (Seat seat :seats) {

            //Kiem tra thu bus da ton tai chua
            int index = -1 ;
            for (int i = 0 ; i<result.size();i++) {
                if(result.get(i).getBus().getId().equals(seat.getBus().getId())){
                    index = i;

                }
                break;
            }

            //If index == -1 => add new ,index!=-1 => update (thêm xe vào)

            if (index != -1 ) {
                result.get(index).getSeats().add(seat.getId());

                // }
            }
            else {
                //if(seat.getBus().getOrigin().equals(tourDTO.getLocationOrigin())){
                BusSeatDTO busSeatDTO = new BusSeatDTO();
                BusDTO busDTO = new BusDTO();
                busDTO.setId(seat.getBus().getId());//ckeck bus này
                busDTO.setPlate(seat.getBus().getPlate());
                busDTO.setName(seat.getBus().getName());
                busDTO.setOrigin(seat.getBus().getOrigin());
                busDTO.setDestination(seat.getBus().getDestination());
                busDTO.setStartTime(seat.getBus().getStartTime());
                busDTO.setEndTime(seat.getBus().getEndTime());
                busDTO.setDistanceTime(seat.getBus().getDistanceTime());
                busDTO.setPriceDefault(seat.getBus().getPriceDefault());

                busSeatDTO.setBus(busDTO);


                List<Long> listSeat = new ArrayList<>();
                listSeat.add(seat.getId());
                busSeatDTO.setSeats(listSeat);
                result.add(busSeatDTO);

            }


        }
        return result;
    }

    @Override
    public List<BusDTO> listBus() {
        List<BusDTO> bookingDTOS = new ArrayList<>();
        List<Bus> bookings = busRepository.findAll();
        for (Bus bus : bookings) {
            BusDTO busDTO = new BusDTO();
            busDTO.setId(bus.getId());//ckeck bus này
            busDTO.setPlate(bus.getPlate());
            busDTO.setName(bus.getName());
            busDTO.setOrigin(bus.getOrigin());
            busDTO.setDestination(bus.getDestination());
            busDTO.setStartTime(bus.getStartTime());
            busDTO.setEndTime(bus.getEndTime());
            busDTO.setDistanceTime(bus.getDistanceTime());
            busDTO.setPriceDefault(bus.getPriceDefault());

            bookingDTOS.add(busDTO);
        }

        return bookingDTOS;
    }
}
