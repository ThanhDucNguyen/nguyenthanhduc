package com.witbus.demo.services;

import com.witbus.demo.dto.OfferDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OfferService {
    List<OfferDTO> getOffer();
}
