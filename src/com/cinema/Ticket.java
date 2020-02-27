package com.cinema;

public class Ticket {
    Session session;
    int cost;
    int row;
    int seat;
    private int cinemaHallNumber;

    Ticket(Session session, int row, int seat){
        this.session = session;
        this.row = row;
        this.seat = seat;
        cost = session.ticketCost;
        cinemaHallNumber = session.cinemaHall.number;

    }

    @Override
    public String toString(){
        return session.movie.name + "\nRow: " + row + "\nSeat: " + seat + "\n" + session.toString();
    }
}
