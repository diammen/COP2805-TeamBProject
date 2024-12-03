// edu.easternflorida.LumpkinR
package teamb;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MovieTheaterDbSetup {
    // JavaDB connection URL
    private static final String DB_URL = "jdbc:derby:MovieTheaterDB;create=true";
    
    public static void CreateDatabase() {
        if (Files.isDirectory(Paths.get("MovieTheaterDB"))) {
            System.out.println("Database already exists");
        } else {
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement statement = connection.createStatement()) {

                System.out.println("Connected to the JavaDB Theater database successfully.");

                // Theater Table
                String createTheaterTable = """
                        CREATE TABLE Theater (
                            theater_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            theater_name VARCHAR(255) NOT NULL,
                            location VARCHAR(255)
                        )
                        """;
                statement.executeUpdate(createTheaterTable);

                // Language Table
                String createLanguageTable = """
                        CREATE TABLE Language (
                            language_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            language_name VARCHAR(255) NOT NULL UNIQUE
                        )
                        """;
                statement.executeUpdate(createLanguageTable);

                // Customer Table
                String createCustomerTable = """
                        CREATE TABLE Customer (
                            customer_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            email VARCHAR(255) NOT NULL UNIQUE,
                            phone VARCHAR(20) NOT NULL UNIQUE,
                            address VARCHAR(255)
                        )
                        """;
                statement.executeUpdate(createCustomerTable);

                String insertDefaultCustomer = """
                        INSERT INTO Customer (name, email, phone, address) 
                        VALUES ('John Doe', 'john.doe@example.com', '123-456-7890', '123 Main St, Hometown, USA')
                        """;
                statement.executeUpdate(insertDefaultCustomer);

                // Movie Table
                String createMovieTable = """
                        CREATE TABLE Movie (
                            movie_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            title VARCHAR(100) NOT NULL,
                            genre VARCHAR(50) NOT NULL,
                            duration INT NOT NULL,
                            release_date DATE,
                            language VARCHAR(50) NOT NULL
                        )
                        """;
                statement.executeUpdate(createMovieTable);

                // Screen Table
                String createScreenTable = """
                        CREATE TABLE Screen (
                            screen_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            screen_name VARCHAR(100) NOT NULL,
                            theater_id INT NOT NULL,
                            total_rows INT NOT NULL
                        )
                        """;
                statement.executeUpdate(createScreenTable);

                // Seat Table
                String createSeatTable = """
                        CREATE TABLE Seat (
                            seat_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            seat_number INT NOT NULL,
                            row_number INT NOT NULL,
                            screen_id INT NOT NULL,
                            CONSTRAINT fk_screen FOREIGN KEY (screen_id) REFERENCES Screen(screen_id),
                            UNIQUE (seat_number, row_number, screen_id)
                        )
                        """;
                statement.executeUpdate(createSeatTable);

                // Showtime Table
                String createShowtimeTable = """
                       CREATE TABLE Showtime (
                        showtime_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        screen_id INT NOT NULL,
                        movie_id INT NOT NULL,
                        showdate_time TIMESTAMP NOT NULL,
                        price DECIMAL(8, 2) DEFAULT 10.00 NOT NULL,
                        CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES Movie(movie_id)
                        )
                        """;
                statement.executeUpdate(createShowtimeTable);

                // Booking Table
                String createBookingTable = """
                        CREATE TABLE Booking (
                            booking_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            showtime_id INT NOT NULL,
                            booking_date TIMESTAMP NOT NULL,
                            customer_id INT NOT NULL,
                            total_price DECIMAL(8, 2) DEFAULT 10.00 NOT NULL,
                            CONSTRAINT fk_showtime FOREIGN KEY (showtime_id) REFERENCES Showtime(showtime_id),
                            CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
                        )
                        """;
                statement.executeUpdate(createBookingTable);

                // BookingSeat Table
                String createBookingSeatTable = """
                CREATE TABLE BookingSeat (
                    booking_seat_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    seat_id INT NOT NULL,
                    booking_id INT NOT NULL,
                    CONSTRAINT fk_booking FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
                )
                """;
                statement.executeUpdate(createBookingSeatTable);

                System.out.println("Tables created successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CreateDatabase();
    }
}