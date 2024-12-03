/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamb;

/**
 *
 * @author Ryan
 */
import java.sql.*;
public class ScreenInserter {
    private static final String DBURL = "jdbc:derby:MovieTheaterDB";

    public static void insertScreens(int screenId) {
        String query = """
            INSERT INTO Screen (screen_name, theater_id, total_rows)
            SELECT 'Screen' || ROW_NUMBER() OVER() AS screen_name, theater_id, total_rows
            FROM (SELECT theater_id, total_rows FROM Screen WHERE screen_id = ?) AS temp
            FETCH FIRST 4 ROWS ONLY;
        """;

        try (Connection conn = Connecting.letConnect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, screenId);  // Set the screen_id dynamically

            int rowsInserted = ps.executeUpdate();
            System.out.println(rowsInserted + " rows inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int screenId = 1;  // Example screen_id to insert the data under
        insertScreens(screenId);  // Call the insert method with the screen_id
    }
}