package com.example.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by msoldevi on 23/02/2017.
 */
@ProcessingGroup("order")
@Component
public class LoggingEventHandlerORder {

    @EventHandler
    public void on(Object event) {
        System.out.println("EventRecieved:"+ event.toString());
    }
}
