package com.example.customers;

import com.example.coreapi.PaymentAcceptedEvent;
import com.example.coreapi.PaymentRejectedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

/**
 * Created by msoldevi on 15/03/2017.
 */

@Saga
public class CustomerSaga {
    @Inject
    private transient CommandGateway commandGateway;

    private String orderId;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentAcceptedEvent e) {
        orderId = e.getOrderId();
        RestTemplate restTemplate = new RestTemplate();
        String obj = restTemplate.getForObject("http://localhost:8082/confirmOrder/"+ e.getCost()+"/"
                +e.getCustomerId()+"/"+e.getOrderId()+"/", String.class);
        System.out.println("coinfirmOrder called");
        end();
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentRejectedEvent e) {
        orderId = e.getOrderId();
        RestTemplate restTemplate = new RestTemplate();
        String obj = restTemplate.getForObject("http://localhost:8082/rejectOrder/"+ e.getCost()+"/"
                +e.getOrderId()+"/", String.class);
        System.out.println("RejectORder called");
        end();
    }
}
