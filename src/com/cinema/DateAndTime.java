package com.cinema;

public class DateAndTime {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minutes;

    DateAndTime(String year, String month, String day, String hour, String minutes){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return year + " " + month + " " + day + ", " + hour + ":" + minutes;
    }

}
