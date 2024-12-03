// Brianna Hart
// Edits by Marcel Dao
package teamb;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Showtime {

    private int showtimeId;
    private int screenId;
    private String showDateTime;
    private int theaterId;
    private int movieId;
    private double price;

    public Showtime(int showtimeId, int screenId, String showDateTime, int theaterId, int movieId, double price) {
        this.showtimeId = showtimeId;
        this.screenId = screenId;
        this.showDateTime = showDateTime;
        this.theaterId = theaterId;
        this.movieId = movieId;
        this.price = price;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(String showDateTime) {
        this.showDateTime = showDateTime;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public static int getMovieId(String title) {
        int movieId = 0;
        String query = """
                       SELECT movie_id
                        FROM APP.movie m
                        WHERE m.title = ?
                       """;
        
        try (Connection conn = Connecting.letConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, title);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    movieId = rs.getInt("movie_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        String title = "";
        String query = """
                      SELECT title
                        FROM APP.Movie m
                        WHERE m.movie_id = ?
                      """;

        try (Connection conn = Connecting.letConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, movieId);

            try (ResultSet rs = ps.executeQuery()) {
                title = rs.getString("title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Showtime{"
                + "showtimeId=" + showtimeId
                + ", screenId='" + screenId + '\''
                + ", showDateTime='" + showDateTime + '\''
                + ", theaterId=" + theaterId
                + ", movieId=" + movieId
                + ", price=" + price
                + '}';
    }

    public static void insertShowtime(int screenId, LocalDate date, String clockTime, String movieTitle, double price) {
        String query = "INSERT INTO Showtime (screen_id, movie_id, showdate_time, price)" + "VALUES (?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(clockTime, formatter);
        LocalDateTime calDate = LocalDateTime.of(date, time);
        Timestamp timestamp = Timestamp.valueOf(calDate);

        try (Connection conn = Connecting.letConnect(); PreparedStatement ps = conn.prepareCall(query)) {
            ps.setInt(1, screenId);
            ps.setInt(2, getMovieId(movieTitle));
            ps.setTimestamp(3, timestamp);
            ps.setDouble(4, price);
            ps.executeUpdate();
            System.out.println("Inserted showtime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Showtime> getShowtimes() {
        ArrayList<Showtime> showtimes = new ArrayList<>();
        
        String query = """
                       SELECT * FROM APP.SHOWTIME
                       """;
        
        try (Connection conn = Connecting.letConnect();
                PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Date resultDate = rs.getDate(5);
                    Timestamp ts = rs.getTimestamp(4);
                    LocalDateTime date = ts.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
                    date.format(formatter);
                    int day = date.getDayOfMonth();
                    int month = date.getMonthValue();;
                    int year = date.getYear();
                    
                    Showtime s = new Showtime(rs.getInt(1), rs.getInt(2), date.format(formatter), 1, rs.getInt(3), rs.getDouble(5));
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return showtimes;
    }
}
