package com.example.customers;

import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;
import com.example.coreapi.*;
import com.example.*;
import static org.junit.Assert.*;

/**
 * Created by msoldevi on 17/02/2017.
 */
public class CustomerTest {

    private FixtureConfiguration<Customer> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Customer.class);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateCustomerCommand("1234", 1000))
                .expectEvents(new CustomerCreatedEvent("1234", 1000));
    }
    
    @Test
    public void testBuyingPermited() throws Exception {
        fixture.given(new CustomerCreatedEvent("1234", 1000))
                .when(new ReserveCreditCommand("1234", "1111", 12))
                .expectEvents(new PaymentAcceptedEvent("1111", 12, "1234"));
    }
    
    @Test
    public void testBuyingNonPermited() throws Exception {
        fixture.given(new CustomerCreatedEvent("1234", 1000))
                .when(new ReserveCreditCommand("1234", "1111", 1001))
                .expectEvents(new PaymentRejectedEvent("1111", 1001, "1111"));
    }

}