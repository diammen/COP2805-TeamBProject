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
import java.time.ZoneId;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class FindCustomerHandler implements EventHandler<ActionEvent> {
    
    //private Label label;
   
    public FindCustomerHandler(Label label) { this.label = label; }
    @Override
    public void handle(ActionEvent t) {
        int customer_ID = GetCustomer("John");
        String value = String.format("Customer ID: %d", GetCustomer("John"));
        label.setText(value);
    }//
    
    public static int GetCustomer(String name) {
        String sql = "SELECT customer_id FROM APP.CUSTOMER where NAME = ?";
        int customer_ID = 0;
        try(Connection conn = Connecting.letConnect();
            PreparedStatement preparedStatement = conn.prepareStatement(name)) {
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
    
    public static ArrayList<String> getHistory(int customer_ID){
        ArrayList<String> moviePurchase = new ArrayList<>();
        
        ArrayList<Integer> showtimeID = new ArrayList<>();
        
        String sql = "SELECT showtime_id FROM APP.BOOKING where customer_id = ?";
        
       
        
        try(Connection conn = Connecting.letConnect();
            PreparedStatement preparedStatement = conn.prepareStatement(customer_ID)) {
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
            
        try (Connection conn = Connecting.letConnect();
                PreparedStatement ps = conn.prepareCall(showtimeID)) {
            try (ResultSet rs = ps.execute()){
                while (rs.next()){
                    Time showTimeTime = rs.getTime(0);
                    Date showDate = rs.getDate(1);
                    LocalDate date = Instant.ofEpochMilli(showTimeTime.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    int day = date.getDayOfMonth();
                    int month = date.getMonthValue();;
                    int year = date.getYear();        
                            
                            
                    int Screen_ID = rs.getInt(2);
                    int movie_ID = rs.getInt(3);
                    double price = rs.getDouble(4);
                    
                    moviePurchase.add(day + month + year + showDate + Screen_ID + movie_ID + price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
