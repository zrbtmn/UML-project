package com.cinema;

public class Main {

    public static void main(String[] args) {

        TerminalsSystem terminalsSystem = new TerminalsSystem();
	    UI ui = new UI(terminalsSystem);
        while (true) {
            ui.showMovieTable();
            ui.chooseMovie();
            ui.chooseSession();
            ui.chooseSeats();
            ui.chooseOption();
        }
    }
}
