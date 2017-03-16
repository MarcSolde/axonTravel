/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.coreapi
import org.axonframework.commandhandling.TargetAggregateIdentifier

/*

  @author msoldevi
  Created on 20-feb-2017
*/

class CreateOrderCommand(val orderId: String, val customerId: String, val cost: Int)
class ApproveOrderCommand(@TargetAggregateIdentifier val orderId: String, val customerId: String)
class RejectOrderCommand(@TargetAggregateIdentifier val orderId: String)

class OrderCreatedEvent(val orderId: String, val customerId: String, val cost: Int)
class OrderRejectedEvent(val orderId: String)
class OrderAcceptedEvent(val orderId: String, val customerId: String, val cost: Int)