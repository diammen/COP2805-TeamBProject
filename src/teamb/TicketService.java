/*
 * hopefully fixed ticketservice class
 * author: LumpkinR
 * 
 */
package teamb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class TicketService {

    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";

    //purchase tickets for given showtime and seats
    public static void PurchaseTicket(int showtimeId, int customerId, int seatId) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            connection.setAutoCommit(false);

            double price = 0.00;
            String getPriceQuery = "SELECT price FROM Showtime WHERE showtime_id = ?";
            try (PreparedStatement psPrice = connection.prepareStatement(getPriceQuery)) {
                psPrice.setInt(1, showtimeId);
                try (ResultSet rs = psPrice.executeQuery()) {
                    if (rs.next()) {
                        price = rs.getDouble("price");
                    }
                }
            }

            String insertBookingSQL = """
                INSERT INTO Booking (showtime_id, customer_id, booking_date, total_price)
                VALUES (?, ?, CURRENT_TIMESTAMP, ?)
                """;
            try (PreparedStatement ps = connection.prepareStatement(insertBookingSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, showtimeId);
                ps.setInt(2, customerId);
                ps.setDouble(3, price);
                ps.executeUpdate();

                int bookingId;
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bookingId = generatedKeys.getInt(1);
                    } else {
                        throw new Exception("Could not retrieve booking ID");
                    }
                }

                String insertBookingSeatsSQL = """
                            INSERT INTO BookingSeat (seat_id, booking_id)
                            VALUES (?, ?)
                            """;
                try (PreparedStatement seatStatement = connection.prepareStatement(insertBookingSeatsSQL)) {
                    seatStatement.setInt(1, seatId);
                    seatStatement.setInt(2, bookingId);
                    seatStatement.executeUpdate();
                }
                connection.commit();
                System.out.println("Tickets purchased successfully");
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //example
        int showtimeId = 1;
        int customerId = 1;
        List<Integer> seatIds = List.of(1, 2);//assuming list of valid seats
        //TicketService.PurchaseTicket(showtimeId, customerId, seatIds);
    }
}
