package com.example;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by msoldevi on 23/02/2017.
 */

@Component
public class LoggingEventHandler {

    @EventHandler
    public void on(Object event) {
        System.out.println("EventRecieved:"+ event.toString());
    }
}
