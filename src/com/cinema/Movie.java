package com.cinema;

import java.util.ArrayList;

public class Movie {
    String name;
    String description;
    ArrayList<Session> sessions = new ArrayList<>();

    Movie (String name, String description){
        this.name = name;
        this.description = description;
    }

    void addSession(Session session){
        sessions.add(session);
    }
}
