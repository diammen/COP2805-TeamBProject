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
import java.util.ArrayList;

public class Movie {
    
    
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
        this.duration = minutes;
        this.durationString = (minutes/60) + " Hours " + (minutes%60) + " Minutes";
        this.language = language;
        this.release_Date = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.of(relYear, relMonth, relDay));
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setLang(String language) {
        this.language = language;
    }
    
    public void setDuration(int minutes) {
        this.duration = duration; //Literal Duration by minutes
        this.durationString = (minutes/60) + " Hours " + (minutes%60) + " Minutes"; //Pretty version of minutes.
    }
    
    public void setRelease(int relDay, int relMonth, int relYear){
        this.release_Date = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.of(relYear, relMonth, relDay));
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public String getLang() {
        return language;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public String getRelease(){
        return release_Date;
    }
    @Override
    public String toString() {
        return String.format("%s: %s, %d minutes, %s, %s", title, genre, duration, language, release_Date);
    }
    
    public static void insertMovie(String title, String genre, String language, int minutes, int relDay, int relMonth, int relYear){
        
        int durationS = minutes;
        String release_DateS = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.of(relYear, relMonth, relDay));
        String sql = "INSERT INTO Movie (title, genre, duration, language, release_date)" + "VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = Connecting.letConnect() ;
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setInt(3, durationS);
            ps.setString(4, language);
            ps.setString(5, release_DateS);
            ps.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // @author Marcel Dao
    public static ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        
        String query = """
                       SELECT * FROM APP.MOVIE
                       """;
        try (Connection conn = Connecting.letConnect();
                PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Date resultDate = rs.getDate(5);
                    LocalDate date = Instant.ofEpochMilli(resultDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    int day = date.getDayOfMonth();
                    int month = date.getMonthValue();;
                    int year = date.getYear();
                    
                    movies.add(new Movie(rs.getString(2), rs.getString(3), 
                            rs.getInt(4), rs.getString(6),
                            day, month, year));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
