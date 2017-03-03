package com.example.orders;

import com.example.coreapi.CreateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by msoldevi on 01/03/2017.
 */
@Controller
@SpringBootApplication
public class App {

    @Autowired
    CommandGateway commandGateway;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/buyProd/")
    @ResponseBody
    public String buyProd(@RequestParam("custId") String custId, @RequestParam("cost") String cost ) {
        String s = UUID.randomUUID().toString();
        commandGateway.send(new CreateOrderCommand(s , custId, Integer.parseInt(cost)));
        return s;
    }

    @GetMapping("/testOrders/")
    @ResponseBody
    public String test2() {
        return "hello-lo-lo";
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("orderEvents").build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("orderEvents").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareQueue(queue());
        admin.declareExchange(exchange());
        admin.declareBinding(binding());
    }
}
