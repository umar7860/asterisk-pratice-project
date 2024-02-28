package com.offer.subscriptions.utils;

import org.asteriskjava.fastagi.DefaultAgiServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AgiComponent {

    private boolean serviceRunning = false;
    private final DefaultAgiServer defaultagiserver;

    public AgiComponent() {
        defaultagiserver = new DefaultAgiServer();
    }

    @Async
    public void startAgiService() throws IOException {
        defaultagiserver.startup();
        serviceRunning = true;
    }

    public void stopAgiService() {
        defaultagiserver.shutdown();
        serviceRunning = false;
    }
}