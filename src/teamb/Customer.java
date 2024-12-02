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
public class Customer {
    public static Connection letConnect() {
        Connection conn = null;
        
        String url ="jdbc:derby:MovieTheaterDB";
        
        
     
        
        try {Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        return conn;
    }
    
    private String sql;
    
    private int customer_id;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    private void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    private void setEmail(String email) {
        this.email = email; //Literal Duration by minutes
        
    }
    
    private void setAddress(String address){
        this.address = address;
    }
    
    private int getCustomerID() {
        return customer_id;
    }
    
    private String getName() {
        return name;
    }
    
    private String getEmail() {
        return email;
    }
    
    private String getAddress(){
        return address;
    }
    
    Customer(String name, String email, String phone, String address){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    void insertCustomer(){
        String sql = "INSERT INTO Customer (name, email, phone, address" + "VALUES (?, ?, ?, ?)";
        try(Connection conn = letConnect() ;
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
