package com.witbus.demo.services;

import com.witbus.demo.dao.models.Bus;
import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusOwnerDTO;

import java.util.List;

public interface WebService {
    BusOwnerDTO loginManager(BusOwnerDTO busOwnerDTO);

    List<BusDTO> listXe(Long id);
}
