/*
 * Movie Ticket Class
 * 
 * author: LumpkinR
 * 
 */

package teamb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovieTicket {
    private String movieTitle;
    private int ticketId;
    private LocalDateTime showDateTime;
    private double price;
    private String screenName;
    private String seatNumber;
    private String customerName;
    private int theaterName;

    //constructor
    public MovieTicket(int ticketId, String movieTitle, LocalDateTime showDateTime, double price, String screenName, String seatNumber) {
        this.ticketId = ticketId;
        this.movieTitle = movieTitle;
        this.showDateTime = showDateTime;
        this.price = price;
        this.screenName = screenName;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.theaterName = theaterName;
    }
//getters and setters
    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public String getMovieTitle() {
        return movieTitle;
    }
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }
    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getTheaterName() {
        return theaterName;
    }
    public void setTheaterName(int theaterName) {
        this.theaterName = theaterName;
    }
    
    //override toString method
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("""
                ------------------------
                Movie Ticket
                ------------------------
                Ticket ID: %d
                Movie Title: %s
                Theater Name: %s
                Screen Name: %s
                Seat Number: %s
                Show Date and Time: %s
                Customer Name: %s
                Price: %.2f
                ------------------------
                """,
                ticketId, movieTitle, theaterName, screenName, seatNumber, showDateTime.format(formatter),
                 customerName, price);
    }

    //print to console
    public void printTicket() {
        System.out.println(this.toString());
    }
}