package teamb;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class GetOccupantsHandler implements EventHandler<ActionEvent> {
    private static final String DB_URL = "jdbc:derby:MovieTheaterDB";
    private Label label;

    public GetOccupantsHandler(Label label) {
        this.label = label;
    }
    @Override
    public void handle(ActionEvent t) {
        int totalOccupants =  GetOccupants(1);
        String value = String.format("Theater Occupants: %d", GetOccupants(1));
        label.setText(value);
    }

    // method for getting number of people in theater
    public static int GetOccupants(int theaterId) {
        int totalOccupants = 0;

        String query = """
                SELECT COUNT(b.booking_id) AS number_of_People
                FROM booking b
                INNER JOIN Showtime s ON b.showtime_id = s.showtime_id
                INNER JOIN Screen sc ON s.screen_id = sc.screen_id
                WHERE sc.theater_id = ?
                """;

                try (Connection connection = DriverManager.getConnection(DB_URL);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                         preparedStatement.setInt(1, theaterId);

                         try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            while (resultSet.next()) {
                                totalOccupants += resultSet.getInt("number_of_People");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return totalOccupants;
    }
}