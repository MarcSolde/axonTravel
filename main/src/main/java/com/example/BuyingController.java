package com.example;

import com.example.coreapi.CreateCustomerCommand;
import com.example.coreapi.CreateOrderCommand;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by msoldevi on 23/02/2017.
 */

@Controller
public class BuyingController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping("/init")
    @ResponseBody
    public String sendMessage() {
        commandGateway.send(new CreateCustomerCommand("1234", 1000), LoggingCallback.INSTANCE);
        String custId = UUID.randomUUID().toString();
        commandGateway.send(new CreateOrderCommand(custId,"1234", 100), LoggingCallback.INSTANCE);
        System.out.println("postOrder");
        return "OK "+custId;
    }
/*
    @PostMapping("/newCust/")
    public String createCustomer(@RequestParam(value ="custId", required = false) String custId,@RequestParam("money") String money) {
        commandGateway.send(new CreateCustomerCommand((custId == null) ? UUID.randomUUID().toString() : custId, Integer.parseInt(money)));
        return "Customer Created!";
    }

    @PostMapping("/buyProd/")
    @ResponseBody
    public String buyProd(@RequestParam("custId") String custId, @RequestParam("cost") String cost ) {
        commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), custId, Integer.parseInt(cost)));
        return "A-OK";
    }*/
}
