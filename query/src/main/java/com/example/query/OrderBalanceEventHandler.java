package com.example.query;

import com.example.coreapi.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created by msoldevi on 24/02/2017.
 */

@ProcessingGroup("order")
@RestController
public class OrderBalanceEventHandler {

    @Inject
    private transient CommandGateway commandGateway;

    private final OrderBalanceRepository repository;

    public OrderBalanceEventHandler(OrderBalanceRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(OrderCreatedEvent e) {
        commandGateway.send(new ReserveCreditCommand(e.getCustomerId(), e.getOrderId(), e.getCost()));
    }

    @EventHandler
    public void on(OrderAcceptedEvent e) {
        repository.save(new OrderBalance(e.getOrderId(), 0)); //TODO: Change this Hardcoded BS
    }

    @GetMapping("/order/{id}")
    public OrderBalance getOrders(@PathVariable String id) {
        return repository.findOne(id);
    }

    @GetMapping("/order")
    public List<OrderBalance> getOrders() {
        return repository.findAll();
    }

}
