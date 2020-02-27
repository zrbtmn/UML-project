package com.cinema;

public class CinemaHall {

    Integer[][] seats;
    Integer number;

    CinemaHall(Integer number){
        this.number = number;
        seats = new Integer[10][10];
        for (int row = 0; row < 10; row++) {
            for (int seat = 0; seat < 10; seat++) {
                seats[row][seat] = 0;
            }
        }
    }

}
