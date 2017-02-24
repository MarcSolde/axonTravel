package com.example.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Created by msoldevi on 17/02/2017.
 */

class CreateCustomerCommand(val customerId: String, val money: Int)
class ReserveCreditCommand(@TargetAggregateIdentifier val customerId: String, 
                           val orderId: String, val money: Int)
class UpdateDBCommand(val orderId: String, val money: Int, val customerId: String)

class CustomerCreatedEvent(val customerId: String, val money: Int)
class PaymentRejectedEvent(val orderId: String, val money: Int, val customerId: String)
class PaymentAcceptedEvent(val orderId: String, val money: Int, val customerId: String)
class UpdateDBAfterPaymentEvent(val orderId: String, val money: Int, val customerId: String)