package com.witbus.demo.controllers;

import com.witbus.demo.dto.BusDTO;
import com.witbus.demo.dto.BusSeatDTO;
import com.witbus.demo.dto.Response;
import com.witbus.demo.dto.TourDTO;
import com.witbus.demo.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping(value = "/search")
    public Response<List<BusDTO>> searchTour(@RequestBody BusDTO busDTO) {
        List<BusDTO> busDTOList = searchService.searchTour(busDTO);
        return new Response<>(true, busDTOList, "Succsess full!");
    }
    @GetMapping(value = "/listOriginDestination")
    public Response<List<BusDTO>> listOriginDestination() {
        List<BusDTO> busDTOS = searchService.listBus();
        return new Response<>(true, busDTOS, "Succsess full!");
    }
}
