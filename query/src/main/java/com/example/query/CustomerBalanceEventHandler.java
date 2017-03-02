package com.example.query;

import com.example.coreapi.CustomerCreatedEvent;
import com.example.coreapi.PaymentAcceptedEvent;
import com.example.coreapi.UpdateDBAfterPaymentEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msoldevi on 24/02/2017.
 */


//TODO: maybe put this class inside App.java class
@ProcessingGroup("customer")
@RestController
public class CustomerBalanceEventHandler {

    private final CustomerBalanceRepository repository;

    public CustomerBalanceEventHandler(CustomerBalanceRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        System.out.println("AAA");
        repository.save(new CustomerBalance(event.getCustomerId(), event.getMoney()));
    }

    @EventHandler
    public void on(UpdateDBAfterPaymentEvent e) {
        System.out.println("Ayyyyyy");
        repository.save(new CustomerBalance(e.getCustomerId(), repository.findOne(e.getCustomerId()).getMoney() - e.getMoney()));
    }


    @GetMapping("/balance/{id}")
    public CustomerBalance getBalance(@PathVariable String id) {
        return repository.findOne(id);
    }
}
