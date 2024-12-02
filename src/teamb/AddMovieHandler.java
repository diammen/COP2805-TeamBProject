package teamb;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

// Event handler for adding a new movies
class AddMovieHandler implements EventHandler<ActionEvent> {
    private String title;
    private String genre;
    private int duration;
    private String language;
    private int day, month, year;
    private ObservableList<String> list;

    public AddMovieHandler(String title, String genre, int duration, String language, int day, int month, int year, ObservableList<String> list) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.language = language;
        this.day = day;
        this.month = month;
        this.year = year;
        this.list = list;
    }
    
    @Override
    public void handle(ActionEvent t) {
        // Add SQL report to add movie to database here
        //list.add(field.getText());
    }

}
