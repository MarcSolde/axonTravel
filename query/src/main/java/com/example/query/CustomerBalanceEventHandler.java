package com.example.query;

import com.example.coreapi.CustomerCreatedEvent;
import com.example.coreapi.PaymentAcceptedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by msoldevi on 24/02/2017.
 */


//TODO: maybe put this class inside AppQuery.java class
@ProcessingGroup("customer")
@RestController
public class CustomerBalanceEventHandler {

    private final CustomerBalanceRepository repository;

    public CustomerBalanceEventHandler(CustomerBalanceRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        repository.save(new CustomerBalance(event.getCustomerId(), event.getMoney()));
    }

    @EventHandler
    public void on(PaymentAcceptedEvent e) {
        repository.save(new CustomerBalance(e.getCustomerId(), repository.findOne(e.getCustomerId()).getMoney() - e.getCost()));
    }


    @GetMapping("/balance/{id}/")
    public CustomerBalance getBalance(@PathVariable String id) {
        return repository.findOne(id);
    }

    @GetMapping("/balance/")
    public List<CustomerBalance> getOrders() {
        return repository.findAll();
    }
}
