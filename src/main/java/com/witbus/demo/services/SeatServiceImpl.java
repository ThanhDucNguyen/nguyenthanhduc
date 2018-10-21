package com.witbus.demo.services;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dao.models.Seat;
import com.witbus.demo.dao.repository.BusRepository;
import com.witbus.demo.dao.repository.SeatRepository;
import com.witbus.demo.dto.SeatDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService{
    private SeatRepository seatRepository;
    private BusRepository busRepository;

    public SeatServiceImpl(SeatRepository seatRepository, BusRepository busRepository) {
        this.seatRepository = seatRepository;
        this.busRepository = busRepository;
    }

    @Override
    public List<SeatDTO> listSeat(Long id) {
        List<SeatDTO> seatDTOS = new ArrayList<>();
        Optional<Bus> busOptional = busRepository.findById(id);
        for (Seat seat : busOptional.get().getSeats()) {
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setId(seat.getId());
            seatDTO.setName(seat.getName());
            seatDTO.setSeatType(seat.getSeatType());
            seatDTO.setStatus(seat.getStatus());
            seatDTO.setPrice(seat.getPrice());

            seatDTOS.add(seatDTO);
        }

        return seatDTOS;
    }
}
