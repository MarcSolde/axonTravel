package com.example.query;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by msoldevi on 24/02/2017.
 */
@Entity
public class CustomerBalance {

    @Id
    private String customerId;
    private int money;

    public CustomerBalance() {
    }

    public CustomerBalance(String customerId, int money) {
        this.customerId = customerId;
        this.money = money;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
