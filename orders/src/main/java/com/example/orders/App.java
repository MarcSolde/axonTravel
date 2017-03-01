package com.example.orders;

import com.example.coreapi.CreateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by msoldevi on 01/03/2017.
 */
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
        commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), custId, Integer.parseInt(cost)));
        return "A-OK";
    }
}