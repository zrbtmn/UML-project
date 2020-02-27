package com.cinema;

import java.util.ArrayList;

class TerminalsSystem {
    private DataBase dataBase;
    private Authentication authentication;

    TerminalsSystem(){
        dataBase = new DataBase();
    }

    String login(String phoneNumber){
        authentication = new Authentication(phoneNumber);
        return authentication.authorization(); }

    boolean validation(String smsCode){
        return authentication.validation(smsCode);
    }

    Integer getPoints(String phoneNumber){
        Integer points;
        if (dataBase.checkPhoneNumber(phoneNumber)){
            points = dataBase.getPoints(phoneNumber);
            return points;
        } else {
            dataBase.addPhoneNumber(phoneNumber);
            return 0;
        }
    }

    void addPhoneNumber(String phoneNumber){
        dataBase.addPhoneNumber(phoneNumber);
    }

    boolean checkPhoneNumber(String phoneNumber){
        return dataBase.checkPhoneNumber(phoneNumber);
    }

    void addTicketToDB(Ticket ticket){
        dataBase.addTicket(ticket);
    }

    ArrayList<Movie> getMovies(){
        return dataBase.getMovies();
    }

    Ticket createTicket(Session session, int row, int seat){
        return new Ticket(session, row, seat);
    }

    boolean createPayment(int total, String creditCard){
        Payment payment =  new Payment(total, creditCard);
        return payment.isApproval();
    }

    void addPoints(String phoneNumber, int points){
        dataBase.addPoints(phoneNumber, points);
    }

    boolean removePoints(String phoneNumber, int total){
        return dataBase.removePoints(phoneNumber, total);
    }
}
