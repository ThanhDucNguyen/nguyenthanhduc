package com.witbus.demo.controllers;

import com.witbus.demo.dto.OfferDTO;
import com.witbus.demo.dto.Utils.Response;
import com.witbus.demo.services.OfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(value = "/offer")
    public Response<List<OfferDTO>> busOwner() {
        List<OfferDTO> busOwnerDTOList = offerService.getOffer();
        return new Response<>(true, busOwnerDTOList, "Succsess full!");
    }
}
