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

    public static void insertScreens(int screenId) {
    String query = """
        INSERT INTO Screen (screenname, theater_id, total_rows)
        SELECT 'Screen' || CAST(ROW_NUMBER() OVER() AS VARCHAR(50)) AS screen_name, theater_id, total_rows
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