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
public class Screen {
    private int theater_id;
    private String screen_name;
    private int row_num;
    
    private void setTheater(int theater_id) { this.theater_id = theater_id; }
    
    private void setScreen(String screen_name) { this.screen_name = screen_name; }

    private void setRow(int row_num) { this.row_num = row_num; }
    
    
    private int getTheater() { return theater_id; }
    
    private String getScreen() { return screen_name; }

    private int getRow() { return row_num; }
    
    
    public static void insertScreen(int theater_id, String screen_name, int row_num) {
        String sql = "INSERT INTO Screen (theater_id, screen_name, row_num" + "VALUES (?, ?, ?)";
        try(Connection conn = Connecting.letConnect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, theater_id);
            ps.setString(2, screen_name);
            ps.setInt(3, row_num);
            ps.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
