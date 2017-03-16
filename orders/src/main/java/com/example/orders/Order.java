/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orders;

import com.example.coreapi.*;

import java.io.IOException;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.CommandHandler;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author msoldevi
 */

//@ProcessingGroup("customerso")
@Aggregate
@NoArgsConstructor
public class Order {

    @AggregateIdentifier
    private String orderId;

    private String customerId;
    private int cost;
    private OrderState state;
    
    @CommandHandler
    public Order(CreateOrderCommand cmd) {
        System.out.println("OrderCreated!");
        apply(new OrderCreatedEvent(cmd.getOrderId(), cmd.getCustomerId(), cmd.getCost()));
    }
    
    @EventSourcingHandler
    public void on(OrderCreatedEvent e) {
        this.orderId = e.getOrderId();
        this.state = OrderState.CREATED;
        this.customerId = e.getCustomerId();
        this.cost = e.getCost();
    }
    
    @CommandHandler
    public void handle(ApproveOrderCommand cmd) {
        System.out.println("HO-LA");
        apply(new OrderAcceptedEvent(orderId, customerId, cost));
    }
    
    @EventSourcingHandler
    public void on(OrderAcceptedEvent e) {
        this.state = OrderState.APPROVED;
        System.out.println("orderacceptedEvent");
    }

    @CommandHandler
    public void handle(RejectOrderCommand cmd) {
        apply(new OrderRejectedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderRejectedEvent e) { this.state = OrderState.REJECTED;}

}
