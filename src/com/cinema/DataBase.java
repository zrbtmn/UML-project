package com.cinema;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    private String jokerDescription = "In Gotham City, mentally-troubled comedian Arthur Fleck is disregarded and mistreated by society. \n" +
            "He then embarks on a downward spiral of revolution and bloody crime. \n" +
            "This path brings him face-to-face with his alter-ego: \"The Joker\". ";

    private String fightClubDescription = "An insomniac office worker and a devil-may-care soap-maker form an underground fight club that evolves into something much, much more.";

    private HashMap<String, Integer> accounts = new HashMap<>();
    private ArrayList<Ticket> cashier = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private CinemaHall cinemaHall_1 = new CinemaHall(1);
    private CinemaHall cinemaHall_2 = new CinemaHall(2);
    private DateAndTime day = new DateAndTime("2019", "October", "15", "13", "30");
    private Movie joker = new Movie("Joker", jokerDescription);
    private DateAndTime evening = new DateAndTime("2019", "October", "15", "18", "00");
    private Movie fightClub = new Movie("Fight Club", fightClubDescription);

    DataBase(){
        accounts.put("89681921462", 560);
        accounts.put("89125312354", 420);
        accounts.put("89212351235", 190);

        Session joker_1 = new Session(joker, day, cinemaHall_1, 400);
        joker.addSession(joker_1);

        Session fightClub_1 = new Session(fightClub, day, cinemaHall_1, 500);
        Session fightClub_2 = new Session(fightClub, evening, cinemaHall_2, 550);
        fightClub.addSession(fightClub_1);
        fightClub.addSession(fightClub_2);

        movies.add(joker);
        movies.add(fightClub);
    }

    ArrayList<Movie> getMovies(){
        return movies;
    }

    void addPhoneNumber(String phoneNumber){
        accounts.put(phoneNumber, 0);
    }

    boolean checkPhoneNumber(String phoneNumber){
        return accounts.containsKey(phoneNumber);
    }

    int getPoints(String phoneNumber){
        return accounts.get(phoneNumber);
    }

    void addPoints(String phoneNumber, int points){
        points += accounts.get(phoneNumber);
        accounts.put(phoneNumber, points);
    }

    boolean removePoints(String phoneNumber, int total){
        int tmp = getPoints(phoneNumber);
        tmp = tmp - total;
        if (tmp >= 0){
            accounts.put(phoneNumber, tmp);
            return true;
        }
        else return false;
    }

    void addTicket(Ticket ticket){
        cashier.add(ticket);
    }
}
