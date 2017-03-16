package com.example.orders;

import com.example.coreapi.ApproveOrderCommand;
import com.example.coreapi.CreateOrderCommand;
import com.example.coreapi.RejectOrderCommand;
import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @GetMapping("/confirmOrder/{cost}/{custId}/{orderId}")
    @ResponseBody
    public void confirmOrder(@PathVariable String cost, @PathVariable String custId, @PathVariable String orderId) {
        String s = UUID.randomUUID().toString();
        commandGateway.send(new CreateOrderCommand(orderId , custId, Integer.parseInt(cost)));
        commandGateway.send(new ApproveOrderCommand(orderId, custId));
    }

    @GetMapping("/rejectOrder/{cost}/{orderId}")
    @ResponseBody
    public void rejectOrder(@PathVariable String cost,  @PathVariable String orderId) {
        String s = UUID.randomUUID().toString();
        commandGateway.send(new CreateOrderCommand(orderId , "0", Integer.parseInt(cost)));
        commandGateway.send(new RejectOrderCommand(orderId));
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
