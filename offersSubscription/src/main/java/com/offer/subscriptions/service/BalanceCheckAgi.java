package com.offer.subscriptions.service;


import com.offer.subscriptions.model.Offer;
import com.offer.subscriptions.model.Subscriber;
import com.offer.subscriptions.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.asteriskjava.fastagi.command.SayDigitsCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class BalanceCheckAgi extends BaseAgiScript {

    private SubscriberService subscriberService;

    @Override
    public void service(AgiRequest request, AgiChannel channel) {
        try {
            subscriberService = BeanUtil.getBean(SubscriberService.class);
            String subscriberNumber = request.getCallerIdNumber();
            Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByNumber(subscriberNumber);
            if(subscriberOptional.isPresent()) {
                Subscriber subscriber = subscriberOptional.get();
                String balance = String.valueOf(subscriber.getBalance());
                sayNumber(balance);
//                channel.setVariable("BALANCE",balance);
            } else {
                Subscriber newSubscriber = new Subscriber();
                newSubscriber.setNumber(subscriberNumber);
                subscriberService.saveSubscriber(newSubscriber);
                sayNumber(String.valueOf(newSubscriber.getBalance()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
