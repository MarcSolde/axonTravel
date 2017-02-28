/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orders;

import com.example.coreapi.*;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.CommandHandler;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

/**
 *
 * @author msoldevi
 */

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
        apply(new OrderCreatedEvent(cmd.getOrderId(), cmd.getCustomerId(), cmd.getCost()));
    }
    
    @EventSourcingHandler
    public void on(OrderCreatedEvent e) {
        this.state = OrderState.CREATED;
        this.customerId = e.getCustomerId();
        this.cost = e.getCost();
        this.orderId = e.getOrderId();
    }
    
    @CommandHandler
    public void handle(ApproveOrderCommand cmd) {
        apply(new OrderAcceptedEvent(orderId));
    }
    
    @EventSourcingHandler
    public void on(OrderAcceptedEvent e) {
        this.state = OrderState.APPROVED;
        apply(new UpdateDBAfterPaymentEvent(e.getOrderId(), 100, "1234")); //TODO: Change this harcoded bs
    }

    @CommandHandler
    public void handle(RejectOrderCommand cmd) {
        apply(new OrderRejectedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderRejectedEvent e) { this.state = OrderState.REJECTED;}



}
