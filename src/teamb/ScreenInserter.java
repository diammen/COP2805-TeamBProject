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

    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";

    public static void insertScreens(int screenId) {
        String query = """
        INSERT INTO Screen (screen_name, theater_id, total_rows)
        SELECT 
            'Screen' || CAST(nextval AS CHAR(10)) AS screen_name,
            theater_id,
            total_rows
        FROM (
            SELECT 
                ROW_NUMBER() OVER () AS nextval,
                theater_id,
                total_rows
            FROM Screen
            WHERE screen_id = ?
        ) AS temp
        FETCH FIRST 4 ROWS ONLY
    """;

        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement ps = conn.prepareStatement(query)) {
            // Set the dynamic parameter for the WHERE clause
            ps.setInt(1, screenId);

            // Execute the update and print the number of rows inserted
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
