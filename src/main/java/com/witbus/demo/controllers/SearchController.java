package com.witbus.demo.controllers;

import com.witbus.demo.dto.BusSeatDTO;
import com.witbus.demo.dto.Response;
import com.witbus.demo.dto.TourDTO;
import com.witbus.demo.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response<List<BusSeatDTO>> searchTour(@RequestBody TourDTO tourDTO) {
        List<BusSeatDTO> busSeatDTOList = searchService.searchTour(tourDTO);
        return new Response<>(true, busSeatDTOList, "Succsess full!");
    }
}
