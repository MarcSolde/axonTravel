/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orders;

import com.example.coreapi.*;
import com.example.customers.Customer;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author msoldevi
 */
public class OrderTest {

    private FixtureConfiguration<Order> fixture;
    

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(Order.class);
    }


    @Test
    public void testCreateOrder() throws Exception{
        fixture.given(new CustomerCreatedEvent("1111", 1000))
                .when(new CreateOrderCommand("1234","1111" , 100))
                .expectEvents(new OrderCreatedEvent("1234", "1111",100));
    }
    
    @Test
    public void testAcceptOrder() throws Exception {
        //fixture.given(new CustomerCreatedEvent("1111", 1000)
        //        , new OrderCreatedEvent("1234","1111" , 100))
        fixture.given(new OrderCreatedEvent("1234","1111" , 10))
                .when(new ApproveOrderCommand("1234","1111" ))
                .expectEvents(new OrderAcceptedEvent("1234"));
    }

    @Test
    public void testRejectOrder() throws Exception {
        //fixture.given(new CustomerCreatedEvent("1111", 1000)
        //        , new OrderCreatedEvent("1234","1111" , 10))
        fixture.given(new OrderCreatedEvent("1234","1111" , 10))
                .when(new RejectOrderCommand("1234"))
                .expectEvents(new OrderRejectedEvent("1234"));
    }

}
