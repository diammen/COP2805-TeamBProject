package teamb;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

// Event handler for adding a new movies
class AddMovieHandler implements EventHandler<ActionEvent> {
    private TextField title,
            genre,
            duration,
            language,
            day,
            month,
            year;
    private ObservableList<String> list;

    public AddMovieHandler(TextField title, TextField genre, TextField duration, TextField language, TextField day, TextField month, TextField year, ObservableList<String> list) {
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
        Movie.insertMovie(title.getText(), genre.getText(),
                language.getText(), Integer.parseInt(month.getText()),
                Integer.parseInt(day.getText()), Integer.parseInt(month.getText()),
                Integer.parseInt(year.getText()));
        
    }

}
