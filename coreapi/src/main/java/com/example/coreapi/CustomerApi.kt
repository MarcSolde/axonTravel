package com.example.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Created by msoldevi on 17/02/2017.
 */

class CreateCustomerCommand(val customerId: String, val money: Int)
class ReserveCreditCommand(@TargetAggregateIdentifier val customerId: String, val cost: Int)
class BuyProductCommand(@TargetAggregateIdentifier val customerId: String, val orderId:String, val cost:Int )

class CustomerCreatedEvent(val customerId: String, val money: Int)
class PaymentRejectedEvent(val cost: Int)
class PaymentAcceptedEvent(val cost: Int)
