package com.example.orders;

import com.example.coreapi.*;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by msoldevi on 22/02/2017.
 */
public class OrderSagaTest {
    private SagaTestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new SagaTestFixture<>(OrderSaga.class);
    }

    @Test
    public void testBuyingRequest() throws Exception {
        fixture.givenAPublished(new CustomerCreatedEvent("1111", 1000))
               //.givenNoPriorActivity()
                .whenPublishingA(new OrderCreatedEvent("1234", "1111", 100))
                .expectActiveSagas(1)
                .expectDispatchedCommandsEqualTo(new ReserveCreditCommand("1111", "1234", 100));
    }

    @Test
    public void testPayableOrder() throws Exception {
        fixture.givenAPublished(new CustomerCreatedEvent("1111", 1000))
                .andThenAPublished(new OrderCreatedEvent("1234", "1111", 100))
                .whenPublishingA(new PaymentAcceptedEvent("1234", 100, "1111"))
                .expectDispatchedCommandsEqualTo(new ApproveOrderCommand("1234", "1111"));
    }

    @Test
    public void testUnpayableOrder() throws Exception {
        fixture.givenAPublished(new CustomerCreatedEvent("1111", 1000))
                .andThenAPublished(new OrderCreatedEvent("1234", "1111", 100))
                .whenPublishingA(new PaymentRejectedEvent("1234", 100, "1111"))
                .expectDispatchedCommandsEqualTo(new RejectOrderCommand("1234"));
    }


}