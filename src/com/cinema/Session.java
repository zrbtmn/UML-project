package com.cinema;


public class Session {

    Movie movie;
    private DateAndTime dateAndTime;
    CinemaHall cinemaHall;
    Integer ticketCost;

    public Session(Movie movie, DateAndTime dateAndTime, CinemaHall cinemaHall, Integer ticketCost) {
        this.movie = movie;
        this.dateAndTime = dateAndTime;
        this.cinemaHall = cinemaHall;
        this.ticketCost = ticketCost;
    }

    @Override
    public String toString() {
//        return String.format(dateAndTime.toString() + "\nCinema hall: " + cinemaHall.number + "\nTicket cost: " + ticketCost + " RUB");
        return dateAndTime.toString() + "\nTicket cost: " + ticketCost + " RUB";
    }
}
