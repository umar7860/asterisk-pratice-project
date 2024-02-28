package com.offer.subscriptions;

import com.offer.subscriptions.utils.AgiComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OffersSubscriptionApplication implements CommandLineRunner {
	private final AgiComponent agiComponent;

	public OffersSubscriptionApplication(AgiComponent agiComponent) {
		this.agiComponent = agiComponent;
	}

	public static void main(String[] args) {
		SpringApplication.run(OffersSubscriptionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		agiComponent.startAgiService();
	}
}
