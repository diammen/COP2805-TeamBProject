///*
// * hopefully fixed ticketservice class
// * author: LumpkinR
// * 
// */
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.Timestamp;
//import java.util.List;
//
//public class TicketService {
//
//    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";
//
//
//    //purchase tickets for given showtime and seats
//    public static void PurchaseTicket(int showtimeId, int customerId, List<Integer> seatIds) {
//        try (Connection connection = DriverManager.getConnection(DB_URL)) {
//        connection.setAutoCommit(false);
//
//        String insertBookingSQL = """
//                INSERT INTO Booking (showtime_id, customer_id, booking_date, total_price)
//                VALUES (?, ?, CURRENT_TIMESTAMP, ?)
//                """;
//                try (Statement statement = connection.createStatement(insertBookingSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                    double totalPrice = seatIds.size() * 10.00; //assume $10 per seat
//                    statement.setInt(1, showtimeId);
//                    statement.setInt(2, customerId);
//                    statement.setDouble(3, totalPrice);
//                    statement.executeUpdate();
//
//                    int bookingId;
//                    try (ResultSet generatedKeys = bookingstmt.getGeneratedKeys()) {
//                        if (generatedKeys.next()) {
//                            bookingId = generatedKeys.getInt(1);
//                        } else {
//                            throw new Exception("Could not retrieve booking ID");
//                        }
//                    }
//
//                    String insertBookingSeatsSQL = """
//                            INSERT INTO BookingSeat (seat_id, booking_id)
//                            VALUES (?, ?)
//                            """;
//                            try (Statement seatStatement = connection.createStatement(insertBookingSeatsSQL)) {
//                                for (int seatId : seatIds) {
//                                    seatStatement.setInt(1, seatId);
//                                    seatStatement.setInt(2, bookingId);
//                                    seatStatement.executeUpdate();
//                                }
//                            }
//                            connection.commit();
//                            System.out.println("Tickets purchased successfully");
//                        } catch (Exception e) {
//                            connection.rollback();
//                            throw e;
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//    public static void main(String[] args) {
//        //example
//        int showtimeId = 1;
//        int customerId = 1;
//        List<Integer> seatIds = List.of(1, 2);//assuming list of valid seats
//        TicketService.PurchaseTicket(showtimeId, customerId, seatIds);
//    }
//}