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

    public OrderBalance() {
    }

    public OrderBalance(String orderId, int cost) {
        this.orderId = orderId;
        this.cost = cost;
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
}
