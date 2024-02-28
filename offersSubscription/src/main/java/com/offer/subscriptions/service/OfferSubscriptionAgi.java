package com.offer.subscriptions.service;


import com.offer.subscriptions.model.Offer;
import com.offer.subscriptions.model.Subscriber;
import com.offer.subscriptions.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class OfferSubscriptionAgi extends BaseAgiScript {

    private SubscriberService subscriberService;

    private OfferService offerService;

    @Override
    public void service(AgiRequest request, AgiChannel channel) {
        try {
            subscriberService = BeanUtil.getBean(SubscriberService.class);
            offerService = BeanUtil.getBean(OfferService.class);
            String subscriberNumber = request.getCallerIdNumber();
            String offerType = request.getParameter("offerType");

            Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByNumber(subscriberNumber);
            Optional<Offer> offerOptional = offerService.findOfferByType(offerType);

            if (offerOptional.isPresent()) {
                Offer offer = offerOptional.get();

                if (subscriberOptional.isPresent()) {
                    Subscriber subscriber = subscriberOptional.get();

                    if (subscriber.getOffers().contains(offer)) {
                        streamFile("custom/already_sub");
                        System.out.println("Offer already subscribed");
                    } else {
//                        subscriber = subscriberService.subscribeOffer(subscriber, offer);
                        if(subscriberService.subscribeOffer(subscriber, offer)) {
                            streamFile("custom/success");
                            System.out.println("Offer Subscribed Successfully");
                        } else {
                            streamFile("custom/balance");
                        }

                    }
                } else {
//                    Subscriber newSubscriber = new Subscriber();
//                    newSubscriber.setNumber(subscriberNumber);
//                    newSubscriber = subscriberService.subscribeOffer(newSubscriber, offer);
//                    streamFile("custom/success");
//                    System.out.println("New user added and offer also subscribed");
//                    channel.sayDigits(String.valueOf(newSubscriber.getBalance()));
                }
            } else {
                System.out.println("Invalid-offer selection");
//                streamFile("custom/unknownOption");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
