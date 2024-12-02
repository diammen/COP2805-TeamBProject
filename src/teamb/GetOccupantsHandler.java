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
        String value = String.format("Theater Occupants: %d", GetOccupants(0, 0));
        label.setText(value);
    }
    
    // method for getting number of people in theater
    public static int GetOccupants(int showTimeId, int theaterId) {
        int numberOfPeople = 0;

        String query = """
                SELECT COUNT(b.booking_id) AS number_of_people
                FROM Booking b
                INNER JOIN Showtime s ON b.showtime_id = s.showtime_id
                INNER JOIN Screen sc ON s.screen_id = sc.screen_id
                WHERE s.showtime_id = ? AND sc.theater_id = ?
                """;

                try (Connection connection = DriverManager.getConnection(DB_URL);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setInt(1, showTimeId);
                    preparedStatement.setInt(2, theaterId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            numberOfPeople = resultSet.getInt("number_of_people");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return numberOfPeople;
        }
}
