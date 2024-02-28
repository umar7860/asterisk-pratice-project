package com.offer.subscriptions.service;

import com.offer.subscriptions.model.Offer;
import com.offer.subscriptions.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Optional<Offer> findOfferByType(String type) {
        return offerRepository.findByType(type);
    }
}
