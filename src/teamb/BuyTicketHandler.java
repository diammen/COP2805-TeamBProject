package teamb;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    private DropdownField movie;
    private DateField date;
    private TextField time;
    private TextField seatId;

    public BuyTicketHandler(TextField name, TextField email, TextField phone, TextField address, DropdownField movie, DateField date, TextField time, TextField seatId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.seatId = seatId;
    }

    public void handle(ActionEvent t) {
        try {
            String customerName = name.getText();
            String customerEmail = email.getText();
            String customerPhone = phone.getText();
            String customerAddress = address.getText();
            int movieId = Showtime.getMovieId((String) movie.getField().getValue());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime locTime = LocalTime.parse(time.getText(), formatter);
            LocalDateTime locDateTime = LocalDateTime.of(date.getDatePicker().getValue(), locTime);
            int showtimeId = PurchaseMovieTicket.getShowtimeId((String)movie.getField().getValue(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(locDateTime));

            int customerId = CustomerService.getCustomerId(customerName, customerEmail, customerPhone);
            int seatId = Integer.parseInt(this.seatId.getText());

            TicketService.PurchaseTicket(showtimeId, customerId, seatId);

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
