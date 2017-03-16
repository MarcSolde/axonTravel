package com.example.query;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by msoldevi on 24/02/2017.
 */

@Entity
public class OrderBalance {
    @Id
    private String orderId;
    private int cost;
    private String customerId;
    private String orderState;

    public OrderBalance() {
    }

    public OrderBalance(String orderId, int cost, String customerId, String orderState) {
        this.orderId = orderId;
        this.cost = cost;
        this.customerId = customerId;
        this.orderState = orderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
