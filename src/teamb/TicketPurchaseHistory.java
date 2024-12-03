// edu.easternflorida.LumpkinR
package teamb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketPurchaseHistory {

    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";

    public static void main(String[] args) {
        int customerId = 1; // placeholder for actual Id
        List<MovieTicket> ticketHistory = getTicketPurchaseHistory(customerId);

        if (ticketHistory.isEmpty()) {
            System.out.println("No ticket purchase history found for customer ID: " + customerId);
        } else {
            System.out.println("Ticket purchase history for customer ID: " + customerId);
            for (MovieTicket ticket : ticketHistory) {
                System.out.println(ticket);
            }
        }
    }


public static List<MovieTicket> getTicketPurchaseHistory(int customerId) {
    List<MovieTicket> tickets = new ArrayList<>();
    String query = """
            SELECT
                b.booking_id AS ticket_id,
                c.name AS customer_name,
                m.title AS movie_title,
                s.showdate_time AS showtime,
                b.total_price AS total_price
            FROM
                Booking b
            INNER JOIN Customer c ON b.customer_id = c.customer_id
            INNER JOIN Movie m ON s.movie_id = m.movie_id
            INNER JOIN Showtime s ON b.showtime_id = s.showtime_id
            WHERE
                b.customer_id = ?
                ORDER BY
                    s.showdate_time ASC
            """;
            
    try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setInt(1, customerId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int ticketId = resultSet.getInt("ticket_id");
                String customerName = resultSet.getString("customer_name");
                String movieTitle = resultSet.getString("movie_title");
                String showtime = resultSet.getString("show_time");
                double totalPrice = resultSet.getDouble("total_price");

                //MovieTicket ticket = new MovieTicket(ticketId, customerName, movieTitle, showtime, totalPrice);
                //tickets.add(ticket);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tickets;
  }
}