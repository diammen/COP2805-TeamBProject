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
    private int phone;
    private String address;
    
    private void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    private void setEmail(String email) {
        this.email = email; 
        
    }
    
    private void setPhone(int phone){
        this.phone = phone;
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
    
    private int getPhone() {
        return phone;
    }
    
    private String getPhoneF(){
        String phoneF = Integer.toString(phone);
        String first = phoneF.substring(0, 3);
        String second = phoneF.substring(3, 6);
        String third = phoneF.substring(6);
        phoneF = "(" + first + ") " + second + "-" + third;
        return phoneF;
    }
    private String getAddress(){
        return address;
    }
    
    Customer(String name, String email, int phone, String address){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    public static void insertCustomer(String name, String email, int phone, String address){
        String sql = "INSERT INTO Customer (name, email, phone, address" + "VALUES (?, ?, ?, ?)";
        try(Connection conn = letConnect() ;
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, phone);
            ps.setString(4, address);
            ps.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
