/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamb;

import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Diamm
 */
public class AddShowtimeHandler implements EventHandler<ActionEvent> {
    private DropdownField screen;
    private DatePicker date;
    private TextField clockTime,
            price;
    private DropdownField movie;
    private ObservableList<String> list;
    
    public AddShowtimeHandler(DropdownField screen, DatePicker date,
            TextField clockTime, DropdownField movie,
            TextField price, ObservableList<String> list) {
        this.screen = screen;
        this.date = date;
        this.clockTime = clockTime;
        this.movie = movie;
        this.price = price;
        this.list = list;
    }
    @Override
    public void handle(ActionEvent t) {
        Showtime.insertShowtime((Integer)screen.getField().getValue(), 
                date.getValue(), 
                clockTime.getText(), 
                (String)movie.getField().getValue(), 
                Double.parseDouble(price.getText()));
        var showtimes = Showtime.getShowtimes();
        list.clear();
        for (var s : showtimes) {
            list.add(s.toString());
        }
        System.out.println(showtimes.size());
    }
    
}
