package com.witbus.demo.controllers;

import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.SeatDTO;
import com.witbus.demo.dto.Utils.Response;
import com.witbus.demo.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {
    @Autowired
    private SeatService seatService;

    @RequestMapping(value = "/listSeat/{id}", method = RequestMethod.POST)
    public Response<List<SeatDTO>> seat(@PathVariable(value = "id") Long id){
        List<SeatDTO> seatDTOList = seatService.listSeat(id);
        return new Response<>(true,seatDTOList,"Success list seat !");
    }
}
