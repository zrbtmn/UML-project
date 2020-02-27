package com.cinema;

public class Authentication {
    private SMS_service sms_service;
    private String phoneNumber;

    Authentication(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    String authorization(){
        sendSMS(phoneNumber);
        return sms_service.getSMS();
    }

    boolean validation(String smsCode){
        return sms_service.checkPhoneNumber(smsCode);
    }

    private void sendSMS(String phoneNumber){
        sms_service = new SMS_service(phoneNumber);
    }
}
