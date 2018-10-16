package com.witbus.demo.services;

import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusSeatDTO;
import com.witbus.demo.dto.TourDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<BusSeatDTO> searchTour(TourDTO tourDTO);

    List<BusDTO> listBus();
}
