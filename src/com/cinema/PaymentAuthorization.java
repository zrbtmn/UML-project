package com.cinema;

public class PaymentAuthorization {
    private Payment payment;

    PaymentAuthorization(Payment payment){
        this.payment = payment;
    }

    boolean getApproval(){
        int money = connectToBankSystem(payment.creditCard);
        return money > payment.total;
    }

    private int connectToBankSystem(String creditCard){
        return (int) (Math.random() * 1000 + 200);
    }
}
