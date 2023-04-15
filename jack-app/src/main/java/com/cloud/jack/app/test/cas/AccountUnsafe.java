package com.cloud.jack.app.test.cas;

public class AccountUnsafe {


    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    public void withdraw(Integer amount) {
        balance = balance - amount;
    }

    public Integer getBalance() {
        return balance;
    }
}
