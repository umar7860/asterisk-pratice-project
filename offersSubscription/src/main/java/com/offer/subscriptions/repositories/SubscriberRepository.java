package com.offer.subscriptions.repositories;

import com.offer.subscriptions.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByNumber(String number);
}