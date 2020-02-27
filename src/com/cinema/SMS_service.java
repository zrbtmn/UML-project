package com.cinema;

public class SMS_service {
    private Integer smsCode;
    private String phoneNumber;

    SMS_service(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getSMS(){
        smsCode = (int) (Math.random() * 10000);
        sendSMS(phoneNumber, smsCode.toString());
        return smsCode.toString();
    }

    boolean checkPhoneNumber(String smsCode){
        return this.smsCode.toString().equals(smsCode);
    }

    private void sendSMS(String phoneNumber, String smsCode){}
}
