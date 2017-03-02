package com.example.customers;

import com.example.coreapi.CreateCustomerCommand;
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

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    //TODO: Maybe use inject instead

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/newCust/")
    @ResponseBody
    public String createCustomer(@RequestParam(value ="custId", required = false) String custId, @RequestParam("money") String money) {
        commandGateway.send(new CreateCustomerCommand((custId == null) ? UUID.randomUUID().toString() : custId, Integer.parseInt(money)));
        return "Customer Created!";
    }

    @GetMapping("/test/")
    @ResponseBody
    public String test() {
        return "EZPZ";
    }


    @Bean
    public Queue queue() {
        return QueueBuilder.durable("customerEvents").build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("customerEvents").build();
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
