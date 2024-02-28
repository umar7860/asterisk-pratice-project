package com.offer.subscriptions.service;


import com.offer.subscriptions.model.Offer;
import com.offer.subscriptions.model.Subscriber;
import com.offer.subscriptions.repositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public Optional<Subscriber> findSubscriberByNumber(String number) {
        return subscriberRepository.findByNumber(number);
    }

//    public Subscriber subscribeOffer(Subscriber subscriber, Offer offer) {
//
//        if (subscriber.getBalance() >= offer.getPrice() && !subscriber.getOffers().contains(offer)) {
//            subscriber.setBalance(subscriber.getBalance() - (int) offer.getPrice());
//            subscriber.getOffers().add(offer);
//        }
//        return subscriberRepository.save(subscriber);
//    }

    public boolean subscribeOffer(Subscriber subscriber, Offer offer) {
        if (subscriber.getBalance() >= offer.getPrice() && !subscriber.getOffers().contains(offer)) {
            subscriber.setBalance(subscriber.getBalance() - (int) offer.getPrice());
            subscriber.getOffers().add(offer);
            subscriberRepository.save(subscriber);
            return true;
        }
        return false;
    }

    public Subscriber saveSubscriber(Subscriber s) {
        return subscriberRepository.save(s);
    }


}