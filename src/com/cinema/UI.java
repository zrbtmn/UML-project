package com.cinema;

import java.util.ArrayList;
import java.util.Scanner;

class UI {

    private TerminalsSystem terminalsSystem;
    private String answer;
    private Scanner in;
    private Movie chosenMovie;
    private Session chosenSession;
    private ArrayList<Ticket> cart;

    UI(TerminalsSystem terminalsSystem){
        this.terminalsSystem = terminalsSystem;
        in = new Scanner(System.in);
    }

    void showMovieTable(){
        ArrayList<Movie> movies;
        movies = terminalsSystem.getMovies();
        System.out.println("\n\nTIMETABLE\n");

        for (Movie movie: movies) {
            System.out.println(movie.name);
            System.out.println("-------------------------------");
            System.out.println(movie.description);
            System.out.println("-------------------------------");
            for (Session session: movie.sessions) {
                System.out.println(session.toString());
                System.out.println("-------------------------------");
            }
            System.out.println();
        }
    }

    void chooseMovie(){
        ArrayList<Movie> movies;
        movies = terminalsSystem.getMovies();
        boolean chosen = false;
        int k = 0;
        for (Movie movie : movies) {
            k++;
            System.out.println(k + ") " + movie.name);
        }
        System.out.print("\nChoose movie: ");
        while(!chosen) {
            answer = in.next();
            if (answer.matches("\\d") || answer.matches("\\d\\d")) {
                k = 0;
                for (Movie movie : movies) {
                    k++;
                    if (Integer.toString(k).equals(answer)) {
                        chosenMovie = movie;
                        System.out.println("You've chosen: " + movie.name);
                        chosen = true;
                    }
                }
                if (!chosen) errorOccurred();
            } else errorOccurred();
        }
    }

    void chooseSession(){
        System.out.println("\n-------------------------------");
        boolean chosen = false;
        int k = 0;
        for (Session session: chosenMovie.sessions) {
            k++;
            System.out.println(k + ") " + session.toString());
            System.out.println("-------------------------------");
        }
        System.out.print("\nChoose session: ");
        while(!chosen) {
            answer = in.next();
            if (answer.matches("\\d") || answer.matches("\\d\\d")) {
                k = 0;
                for (Session session : chosenMovie.sessions) {
                    k++;
                    if (Integer.toString(k).equals(answer)) {
                        chosenSession = session;
                        System.out.println("You've chosen: \n");
                        System.out.println("-------------------------------");
                        System.out.println(chosenSession.toString());
                        System.out.println("-------------------------------");
                        chosen = true;
                    }
                }
                if (!chosen) errorOccurred();
            } else errorOccurred();
        }
    }

    void chooseSeats(){
        cart = new ArrayList<>();
        boolean stop = false;
        while (!stop){
            chooseSeat(cart);
            boolean chosen = false;
            while(!chosen) {
                System.out.println("\nAdd another one?");
                System.out.println("1) Yes\n2) No");
                answer = in.next();
                if (answer.matches("\\d")) {
                    if (Integer.toString(1).equals(answer))
                        chosen = true;
                    else if (Integer.toString(2).equals(answer)){
                        chosen = true;
                        stop = true;
                    } else errorOccurred();
                } else errorOccurred();
            }
        }
    }

    private void chooseSeat(ArrayList<Ticket> cart){
        System.out.println("     -----------------SCREEN-----------------");
        for (int row = 0; row < 10; row++) {
            int tmp = row;
            tmp++;
            if (tmp != 10) {
                System.out.print(tmp + ")  ");
            } else System.out.print(tmp + ") ");
            for (int seat = 0; seat < 10; seat++) {
                tmp = seat;
                tmp++;
                if (chosenSession.cinemaHall.seats[row][seat] == 0)
                    System.out.print(" [" + tmp + "]");
                else System.out.print(" [X]");
            }
            System.out.println();
        }
        System.out.println("     ----------------------------------------\n");
        Ticket ticket;
        boolean rowChosen = false;
        boolean seatChosen = false;
        while(!seatChosen) {
            int row, seat;
            while (!rowChosen) {
                System.out.print("\nChoose row: ");
                answer = in.next();
                if (answer.matches("\\d") || answer.matches("\\d\\d")) {
                    for (int i = 1; i <= 10; i++) {
                        if (answer.equals(Integer.toString(i))) {
                            row = i;
                            rowChosen = true;
                            while(!seatChosen) {
                                System.out.print("Choose seat: ");
                                answer = in.next();
                                if (answer.matches("\\d") || answer.matches("\\d\\d")) {
                                    for (seat = 1; seat <= 10; seat++) {
                                        if (answer.equals(Integer.toString(seat))) {
                                            if (chosenSession.cinemaHall.seats[row-1][seat-1] == 0) {
                                                ticket = terminalsSystem.createTicket(chosenSession, row, seat);
                                                chosenSession.cinemaHall.seats[row-1][seat-1] = 1;
                                                cart.add(ticket);
//                                                System.out.println("\nYou've chosen the seat:");
//                                                System.out.println("-------------------------------");
//                                                System.out.println("Row: " + Integer.toString(row) + "\nSeat: " + Integer.toString(seat));
//                                                System.out.println("-------------------------------");
                                                seatChosen = true;
                                                break;
                                            } else {
                                                rowChosen = false;
                                                System.out.println("Seat is already occupied. \nTry again:");
                                            }
                                        }
                                    }
                                } else errorOccurred();
                            }
                        }
                    }
                } else errorOccurred();
            }
        }
    }

    void chooseOption(){
        boolean stop = false;
        int total = 0;
        for (Ticket ticket : cart) {
            total += ticket.cost;
        }
        while (!stop){
            boolean chosen = false;
            while(!chosen) {
                System.out.println("How would you like to pay?\n");
                System.out.println("-------------------------------");
                System.out.println("1) Points\n2) Credit card\n3) Exit");
                System.out.println("-------------------------------\n");
                answer = in.next();
                if (answer.matches("\\d")) {
                    switch (answer) {
                        case "2":
                            chosen = true;
                            if (payCredit(total)) {
                                stop = true;
                            } else chosen = false;
                            break;
                        case "1":
                            chosen = true;
                            if (payPoints(total)) {
                                stop = true;
                            } else chosen = false;
                            break;
                        case "3":
                            stop = true;
                            chosen = true;
                            for (Ticket ticket : cart) {
                                ticket.session.cinemaHall.seats[ticket.row-1][ticket.seat-1] = 0;
                            }
                            break;
                        default:
                            errorOccurred();
                            break;
                    }
                } else errorOccurred();
            }
        }
    }

    private boolean payCredit(int total){
        in = new Scanner(System.in);
        boolean payed = false;
        System.out.println("-------------------------------");
        System.out.println("Total cost: " + total + "RUB");
        System.out.println("-------------------------------");
        for (Ticket ticket : cart){
            System.out.println(ticket.toString());
            System.out.println("-------------------------------");
        }
        boolean entered = false;
        while (!entered) {
            System.out.println("\nEnter your credit card:\n>>");
            answer = in.next();
            if (answer.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d")){
                String creditCard = answer;
                entered = true;
                if (terminalsSystem.createPayment(total, creditCard)) {
                    System.out.println("Success!\n");
                    for (Ticket ticket : cart){
                        terminalsSystem.addTicketToDB(ticket);
                    }
                    int points = (int) (total*0.2);
                    addPoints(points);
                    printReceipt(creditCard);
                    payed = true;
                }
                else {
                    payed = false;
                }
            } else {
                errorOccurred();
            }
        }
        return payed;
    }

    private boolean payPoints(int total){
        in = new Scanner(System.in);
        boolean stop = false;
        System.out.println("-------------------------------");
        System.out.println("Total cost: " + total + "RUB");
        System.out.println("-------------------------------");
        for (Ticket ticket : cart){
            System.out.println(ticket.toString());
            System.out.println("-------------------------------");
        }
        Integer points;
        String smsCode;
        String sms;
        String phoneNumber;
        while(!stop){
            System.out.println("1) Login\n2) Back\n");
            String answer = in.nextLine();
            if (answer.equals("1")){
                phoneNumber = getPhoneNumber();
                if (phoneNumber != null && terminalsSystem.checkPhoneNumber(phoneNumber)){
                    smsCode = terminalsSystem.login(phoneNumber);
                    sms = getSmsCode(smsCode);
                    if (sms != null && terminalsSystem.validation(sms)){
                        if(terminalsSystem.removePoints(phoneNumber, total)){
                            points = terminalsSystem.getPoints(phoneNumber);
                            printReceipt("You have " + points + " points left");
                            stop = true;
                        }
                        else {
                            points = terminalsSystem.getPoints(phoneNumber);
                            System.out.println("You have " + points + " points. It is not enough to pay.");
                        }
                    } else errorOccurred();
                } else System.out.println("You are probably not registered");
            } else if (answer.equals("2")){
                return  false;
            }
        }
        return true;
    }

    private void addPoints(int points){
        boolean stop1 = false;
        while (!stop1) {
            System.out.println("Would you like to add " + points + " Cinema points?\n1) Yes\n2) No");
            answer = in.nextLine();
            if ("1".equals(answer)) {
                boolean stop = false;
                String smsCode;
                String sms;
                String phoneNumber;
                while(!stop){
                    System.out.println("1) Login or register\n2) Back");
                    String answer = in.nextLine();
                    if (answer.equals("1")){
                        phoneNumber = getPhoneNumber();
                        if (phoneNumber != null){
                            if(!terminalsSystem.checkPhoneNumber(phoneNumber))
                                terminalsSystem.addPhoneNumber(phoneNumber);
                            smsCode = terminalsSystem.login(phoneNumber);
                            sms = getSmsCode(smsCode);
                            if (sms != null && terminalsSystem.validation(sms)){
                                System.out.println("Success!\n");
                                terminalsSystem.addPoints(phoneNumber, points);
                                points = terminalsSystem.getPoints(phoneNumber);
                                System.out.println("На вашем счету " + points + " бонусов\n");
                                stop = true;
                                stop1 = true;
                            } else errorOccurred();
                        } else errorOccurred();
                    } else if (answer.equals("2")){
                        stop = true;
                        stop1 = true;
                    }
                }
            }
        }
    }

    private void printReceipt(String creditCard) {
        int total = 0;
        System.out.println("-------------RECEIPT-----------");
        System.out.println("-------------------------------");
        for (Ticket ticket : cart){
            total += ticket.cost;
            System.out.println(ticket.toString());
            System.out.println("-------------------------------");
        }
        System.out.println("-------------------------------");
        System.out.println("Total cost: " + total + " RUB\n\n" + creditCard + "\nAPPROVED");
        System.out.println("-------------------------------");
        boolean finish = false;
        while (!finish){
            System.out.println("Thank you!\nPress any key to get back to Timetable");
            answer = in.next();
            finish = true;
        }
    }

    private String getPhoneNumber(){
        System.out.print("Enter your phone number: \n>>");
        answer = in.next();
        if (answer.length() == 11){
            return answer;
        }
        else return null;
    }

    private String getSmsCode(String sms){
        System.out.print("Enter the code: [" + sms + "]\n>>");
        return in.next();
    }

    private void errorOccurred(){
        System.out.println("Error occurred. Try again:");
    }
}
