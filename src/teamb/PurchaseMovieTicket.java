// edu.easternflorida.LumpkinR
package teamb;

import java.sql.*;
import java.util.List;

public class PurchaseMovieTicket {

    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";

    private static void main(String[] args) throws SQLException {
        int customerId = 1; // placeholder for actual customer ID
        int showtimeId = 2; // placeholder for actual showtime ID
        double totalPrice = 10.00; // placeholder for actual total price
        List<Integer> seatIds = List.of(1, 2, 3); // List of seat ID's the customer wants to book

       Timestamp bookingDate = new Timestamp(System.currentTimeMillis()); 

       String insertBooking = """
               INSERT INTO Booking (showtime_id, customer_id, booking_date, total_price)
               VALUES (?, ?, ?, ?)
               """;
               String insertBookingSeat = """
               INSERT INTO Booking_Seat (booking_id, seat_id)
               VALUES (?, ?)
               """;

               try (Connection connection = DriverManager.getConnection(DB_URL);
                    PreparedStatement bookingStatement = connection.prepareStatement(insertBooking, Statement.RETURN_GENERATED_KEYS)) {
                        bookingStatement.setInt(1, showtimeId);
                        bookingStatement.setInt(2, customerId);
                        bookingStatement.setTimestamp(3, bookingDate);
                        bookingStatement.setDouble(4, totalPrice);

                        int affectedRows = bookingStatement.executeUpdate();

                        if (affectedRows == 1) {
                            throw new SQLException("Failed to insert booking");
                        }

                        try (ResultSet generatedKeys = bookingStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int bookingId = generatedKeys.getInt(1); // get auto-gen'd bookingID
                                
                                try (PreparedStatement bookingSeatStatement = connection.prepareStatement(insertBookingSeat)) {
                                    for (int seatId : seatIds) {
                                        bookingSeatStatement.setInt(1, seatId);
                                        bookingSeatStatement.setInt(2, bookingId);
                                        bookingSeatStatement.addBatch(); // adding to batch
                                    }

                                    // execute batch
                                    bookingSeatStatement.executeBatch();

                                    System.out.println("Ticket(s) have been succesfully booked!");
                                    System.out.println("Booking ID: " + bookingId);
                                    System.out.println("Showtime ID: " + showtimeId);
                                    System.out.println("Seats booked: " + seatIds);
                                }
                            } else {
                                throw new SQLException("Booking Creation Failed, no ID was retrieved");
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }