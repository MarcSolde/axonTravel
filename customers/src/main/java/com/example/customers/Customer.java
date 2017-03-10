package com.example.customers;

import com.example.coreapi.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created by msoldevi on 17/02/2017.
 */

@Aggregate
@NoArgsConstructor
public class Customer {

    @AggregateIdentifier
    private String customerId;
    private int money;

    @CommandHandler
    public Customer(CreateCustomerCommand cmd) {
        System.out.println("test pre senging CustomerCreatedEvent");
        apply(new CustomerCreatedEvent(cmd.getCustomerId(), cmd.getMoney()));
        System.out.println("test post senging CustomerCreatedEvent");
    }


    @EventSourcingHandler
    public void on(CustomerCreatedEvent e) {
        System.out.println("Handler Customer.java");
        this.customerId = e.getCustomerId();
        this.money = e.getMoney();
    }

    @CommandHandler
    public void handle(ReserveCreditCommand cmd) {
        if (money >= cmd.getMoney())  {
            apply(new PaymentAcceptedEvent(cmd.getOrderId(), cmd.getMoney(), cmd.getCustomerId()));
        }
        else {
            apply(new PaymentRejectedEvent(cmd.getOrderId(), cmd.getMoney(), cmd.getCustomerId()));
        }
    }

    @EventSourcingHandler
    public void on(PaymentAcceptedEvent e) {
        this.money -= e.getMoney();
        //apply(new ApproveOrderCommand(e.getOrderId(), e.getCustomerId()));
    }


    @EventSourcingHandler
    public void on(PaymentRejectedEvent e) {

    }
}
