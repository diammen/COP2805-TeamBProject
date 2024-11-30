// edu.easternflorida.LumpkinR

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MovieTheatreDbSetup {
    // Database connection details

    private static final String DB_url = "jdbc:mysql://localhost:3306/MovieTicketingSystem";
    private static final String DB_username = "username";
    private static final String DB_password = "password";

    public static void main(String[] args) {
        try {
                
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(DB_url, DB_username, DB_password);
            Statement statement = connection.createStatement(); {

                //Theater Table

                String createTheaterTable = """
                        CREATE TABLE IF NOT EXISTS Theater (
                        theater_id INT AUTO_INCREMENT PRIMARY KEY,
                        theater_name varchar(255) NOT NULL,
                        location varchar(255)
                        );
                        """;

                statement.executeUpdate(createTheaterTable);


                // Language Table

                String createLanguageTable = """
                        CREATE TABLE IF NOT EXISTS Language (
                        Language_id INT AUTO_INCREMENT PRIMARY KEY,
                        Language_name varchar(255) NOT NULL UNIQUE
                        );
                        """;

                statement.executeUpdate(createLanguageTable);

                //Customer Table

                String createCustomerTable = """
                        CREATE TABLE IF NOT EXISTS Customer (
                        customer_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        phone VARCHAR(20) NOT NULL UNIQUE,
                        address TEXT
                        );
                        """;

                statement.executeUpdate(createCustomerTable);

                //Movie Table

                String createMovieTable = """
                        CREATE TABLE IF NOT EXISTS Movie (
                        movie_id INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        genre VARCHAR(50) NOT NULL,
                        duration INT,
                        release_date DATE
                        language_id INT,
                        FOREIGN KEY (language_id) REFERENCES Language(language_id)
                        );
                        """;

                statement.executeUpdate(createMovieTable);

                //Screen Table

                String createScreenTable = """
                        CREATE TABLE IF NOT EXISTS Screen (
                        screen_id INT AUTO_INCREMENT PRIMARY KEY,
                        screen_name VARCHAR(50) NOT NULL,
                        theater_id INT NOT NULL,
                        total_rows INT NOT NULL,
                        FOREIGN KEY (theater_id) REFERENCES Theater(theater_id)
                        );
                        """;

                statement.executeUpdate(createScreenTable);

                //Seat Table

                String createSeatTable = """
                        CREATE TABLE IF NOT EXISTS Seat (
                        seat_id INT AUTO_INCREMENT PRIMARY KEY,
                        seat_number INT NOT NULL,
                        row_number INT NOT NULL,
                        screen_id INT NOT NULL,
                        FOREIGN KEY (screen_id) REFERENCES Screen(screen_id) ON DELETE CASCADE,
                        UNIQUE (seat_number, row_number, screen_id) 
                        );
                        """;

                statement.executeUpdate(createSeatTable);

                //Showtime Table

                String createShowtimeTable = """
                        CREATE TABLE IF NOT EXISTS Showtime (
                        showtime_id INT AUTO_INCREMENT PRIMARY KEY,
                        screen_id INT NOT NULL,
                        movie_id INT NOT NULL,
                        showdate_time DATETIME NOT NULL,
                        price DECIMAL(8, 2) NOT NULL DEFAULT 0.00,
                        FOREIGN KEY (screen_id) REFERENCES Screen(screen_id) ON DELETE CASCADE,
                        FOREIGN KEY (movie_id) REFERENCES Movie(movie_id) ON DELETE CASCADE
                        CHECK (price >= 0)
                        );
                        """;

                statement.executeUpdate(createShowtimeTable);

                //Booking Table

                String createBookingTable = """
                        CREATE TABLE IF NOT EXISTS Booking (
                        booking_id INT AUTO_INCREMENT PRIMARY KEY,
                        showtime_id INT NOT NULL,
                        booking_date DATETIME NOT NULL DEFAULT NOW(),
                        customer_id INT NOT NULL,
                        total_price DECIMAL(8, 2) NOT NULL,
                        FOREIGN KEY (showtime_id) REFERENCES Showtime(showtime_id) ON DELETE CASCADE,
                        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id) ON DELETE CASCADE
                        );
                        """;

                statement.executeUpdate(createBookingTable);

                //booking_seat Table

                String createBookingSeatTable = """
                        CREATE TABLE IF NOT EXISTS Booking_seat (
                        booking_seat_id INT AUTO_INCREMENT PRIMARY KEY,
                        seat_id INT NOT NULL,
                        booking_id INT NOT NULL,
                        FOREIGN KEY (seat_id) REFERENCES Seat(seat_id) ON DELETE CASCADE,
                        FOREIGN KEY (booking_id) REFERENCES Booking(booking_id) ON DELETE CASCADE
                        );
                        """;

                statement.executeUpdate(createBookingSeatTable);

                System.out.println("Tables created successfully.");
            }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
