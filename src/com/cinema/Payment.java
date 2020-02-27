package com.cinema;

class Payment {
    int total;
    String creditCard;

    Payment(int total, String creditCard){
        this.total = total;
        this.creditCard = creditCard;
    }

    boolean isApproval(){
        PaymentAuthorization paymentAuthorization = new PaymentAuthorization(this);
        return paymentAuthorization.getApproval();
    }
}
