package com.example.orders;

import com.example.coreapi.*;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

/**
 * Created by msoldevi on 22/02/2017.
 */
@Saga
public class OrderSaga {

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCreatedEvent event) {
        commandGateway.send(new ReserveCreditCommand(event.getCustomerId(), event.getOrderId(), event.getCost()), LoggingCallback.INSTANCE);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentAcceptedEvent e) {
        commandGateway.send(new ApproveOrderCommand(e.getOrderId(), e.getCustomerId()), LoggingCallback.INSTANCE);
        end();
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentRejectedEvent e) {
        commandGateway.send(new RejectOrderCommand(e.getOrderId()), LoggingCallback.INSTANCE);
        end();
    }



}
