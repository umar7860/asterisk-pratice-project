package com.offer.subscriptions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriberId;

    private String number;

    private int balance = 100;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subscriber_offer",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private Set<Offer> offers = new HashSet<>();


}

