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
    
    Customer(int customer_id, String name, String email, int phone, String address){
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    public static void insertCustomer(int customer_id, String name, String email, int phone, String address){
        String sql = "INSERT INTO Customer (customer_id, name, email, phone, address" + "VALUES (?, ?, ?, ?, ?)";
        try(Connection conn =  Connecting.letConnect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customer_id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setInt(4, phone);
            ps.setString(5, address);
            ps.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
