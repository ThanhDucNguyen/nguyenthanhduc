package com.witbus.demo.services;

import com.witbus.demo.dao.models.Offer;
import com.witbus.demo.dao.repository.OfferRepository;
import com.witbus.demo.dto.OfferDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<OfferDTO> getOffer() {
        List<OfferDTO> offerDTOS = new ArrayList<>();
        List<Offer> offers = offerRepository.getOffer();
        for (Offer offer:offers){
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setId(offer.getId());
            offerDTO.setName(offer.getName());
            offerDTO.setPrice(offer.getPrice());
            offerDTO.setInfo(offer.getInfo());
            offerDTO.setCode(offer.getCode());

            offerDTOS.add(offerDTO);
        }
        return offerDTOS;
    }
}
