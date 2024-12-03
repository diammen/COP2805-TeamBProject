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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FindCustomerHandler implements EventHandler<ActionEvent> {

    private TextField customerName;
    private ListView text;
    private Label searchResultLabel;
    private ObservableList<String> tickets;

    public FindCustomerHandler(TextField customerName, ListView text, Label searchResultLabel, ObservableList<String> tickets) {
        this.customerName = customerName;
        this.text = text;
        this.searchResultLabel = searchResultLabel;
        this.tickets = tickets;
    }

    @Override
    public void handle(ActionEvent t) {
        int customer_ID = GetCustomer(customerName.getText());
        String value = String.format("Customer ID: %d", customer_ID);
        ArrayList<String> purchaseHistory = getHistory(customer_ID);
        tickets.addAll(purchaseHistory);
        
        text.setItems(tickets);
    }

    public static int GetCustomer(String name) {
        String sql = "SELECT customer_id FROM APP.CUSTOMER where NAME = ?";
        int customer_ID = 0;
        try (Connection conn = Connecting.letConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    customer_ID = resultSet.getInt("customer_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_ID;
    }

    public static ArrayList<String> getHistory(int customer_ID) {
        ArrayList<String> moviePurchase = new ArrayList<>();

        ArrayList<Integer> showtimeID = new ArrayList<>();

        String sql = "SELECT showtime_id FROM APP.BOOKING where customer_id = ?";

        try (Connection conn = Connecting.letConnect(); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer_ID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    showtimeID.add(resultSet.getInt("showtime_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "SELECT * FROM APP.SHOWTIME where showtime_id = ?";

        for (var id : showtimeID) {
            try (Connection conn = Connecting.letConnect(); PreparedStatement ps = conn.prepareStatement(sql2)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Timestamp showTimestamp = rs.getTimestamp(4);
                        LocalDateTime date = showTimestamp.toLocalDateTime();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
                        
                        int day = date.getDayOfMonth();
                        int month = date.getMonthValue();
                        int year = date.getYear();

                        int Screen_ID = rs.getInt(2);
                        int movie_ID = rs.getInt(3);
                        double price = rs.getDouble(5);

                        String currentTicket = String.format("%s %d %d %d\nScreen:%d Movie:%d\nPrice:%.2f", date.format(formatter), day, month, year, Screen_ID, movie_ID, price);

                        moviePurchase.add(currentTicket);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return moviePurchase;
    }
}
