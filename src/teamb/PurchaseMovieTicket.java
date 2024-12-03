
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseMovieTicket {

    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";

    // Query showtime_id based on movie title and showdate_time
    public static int getShowtimeId(String movieTitle, String showDateTime) {
        int showtimeId = -1; // Default if no matching showtime is found
        String query = """
                SELECT showtime_id
                FROM Showtime
                WHERE movie_id = ? AND showdate_time = ?
                """;

        try (Connection connection = DriverManager.getConnection(DB_URL); PreparedStatement ps = connection.prepareStatement(query)) {

            // Get movie_id based on the movie title
            int movieId = getMovieId(movieTitle);
            if (movieId == -1) {
                System.out.println("No matching movie found.");
                return -1;
            }

            // Convert showDateTime string to LocalDateTime object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(showDateTime, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);

            // Set query parameters
            ps.setInt(1, movieId);
            ps.setTimestamp(2, timestamp);

            // Execute query and retrieve result
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    showtimeId = rs.getInt("showtime_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return showtimeId;
    }

    // Helper method to get movie_id from movie title
    private static int getMovieId(String movieTitle) {
        int movieId = -1;
        String query = """
                SELECT movie_id
                FROM Movie
                WHERE title = ?
                """;

        try (Connection connection = DriverManager.getConnection(DB_URL); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, movieTitle);

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

    public static void main(String[] args) {
        // Example usage
        String movieTitle = "The Matrix";
        String showDateTime = "2024-12-10 14:30"; // Assuming the format is "yyyy-MM-dd HH:mm"

        int showtimeId = getShowtimeId(movieTitle, showDateTime);
        if (showtimeId != -1) {
            System.out.println("Showtime ID: " + showtimeId);
        } else {
            System.out.println("No matching showtime found.");
        }
    }
}
