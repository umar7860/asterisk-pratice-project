package com.offer.subscriptions.repositories;

import com.offer.subscriptions.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByType(String type);
}
