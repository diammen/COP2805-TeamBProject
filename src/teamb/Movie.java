/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamb;

/**
 *
 * @author Jaden
 */
import java.sql.*;
import java.time.*;
import java.time.format.*;

public class Movie {
    public static Connection letConnect() {
        Connection conn = null;
        
        String url ="jdbc:derby:MovieTheaterDB";
        
           
        
        try {Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return conn;
    }
    
    private String sql;
    
    private String title;
    private String genre;
    private int duration;
    private String language;
    private String release_Date;
    private String durationString;
    
    Movie(String title, String genre, int minutes, String language, int relDay, int relMonth, int relYear ){
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.durationString = (minutes/60) + " Hours " + (minutes%60) + " Minutes";
        this.language = language;
        this.release_Date = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.of(relYear, relMonth, relYear));
    }
    
    private void setTitle(String title) {
        this.title = title;
    }
    
    private void setGenre(String genre) {
        this.genre = genre;
    }
    
    private void setLang(String language) {
        this.language = language;
    }
    
    private void setDuration(int minutes) {
        this.duration = duration; //Literal Duration by minutes
        this.durationString = (minutes/60) + " Hours " + (minutes%60) + " Minutes"; //Pretty version of minutes.
    }
    
    private void setRelease(int relDay, int relMonth, int relYear){
        this.release_Date = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.of(relYear, relMonth, relYear));
    }
    
    private String getTitle() {
        return title;
    }
    
    private String getGenre() {
        return genre;
    }
    
    private String getLang() {
        return language;
    }
    
    private int getDuration(){
        return duration;
    }
    
    private String getRelease(){
        return release_Date;
    }
    
    void insertCustomer(){
        String sql = "INSERT INTO Movie (title, genre, duration, language, release_Date" + "VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = letConnect() ;
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setInt(3, duration);
            ps.setString(4, language);
            ps.setString(5, release_Date);
            ps.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
