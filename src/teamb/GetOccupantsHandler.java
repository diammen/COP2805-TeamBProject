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
        String value = String.format("Theater Occupants: %d", totalOccupants);
        label.setText(value);
    }

    // method for getting number of people in theater
    public static int GetOccupants(int theaterId) {
        int totalOccupants = 0;

        String query = """
                       SELECT COUNT(*) AS count
                       FROM APP.BOOKING
                       """;

                try (Connection connection = DriverManager.getConnection(DB_URL);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                         try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            while (resultSet.next()) {
                                totalOccupants += resultSet.getInt("count");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return totalOccupants;
    }
}