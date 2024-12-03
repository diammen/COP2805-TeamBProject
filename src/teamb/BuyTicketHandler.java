package teamb;

import java.sql.Connection;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import java.util.Arrays;

public class BuyTicketHandler implements EventHandler<ActionEvent> {
    private TextField name;
    private TextField email;
    private TextField phone;
    private TextField address;
    private TextField movie;
    private TextField showtimeId;
    private TextField seatId;

    public BuyTicketHandler(TextField name, TextField email, TextField phone, TextField address, TextField movie, TextField showtimeId, TextField seatId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.movie = movie;
        this.showtimeId = showtimeId;
        this.seatId = seatId;
    }

    public void handle(ActionEvent t) {
        try {
            int showtimeId = Integer.parseInt(this.showtimeId.getText());
            String customerName = name.getText();
            String customerEmail = email.getText();
            String customerPhone = phone.getText();
            String customerAddress = address.getText();
            int movieId = Integer.parseInt(this.movie.getText());
            int seatId = Integer.parseInt(this.seatId.getText());

            int customerId = CustomerService.getCustomerId(customerName, customerEmail, customerPhone);

            //String[] seatIdStrings = seatId.split(",");
            //Integer[] seatIdsArray = Arrays.stream(seatIdStrings).map(Integer::parseInt).toArray(Integer[]::new);
        
            //TicketService.PurchaseTicket(showtime, customerId, Arrays.asList(seatIdsArray));

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ticket Purchased");
            alert.setHeaderText("null");
            alert.setContentText("Ticket purchased successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("null");
            alert.setContentText("An error occurred while purchasing the ticket: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}