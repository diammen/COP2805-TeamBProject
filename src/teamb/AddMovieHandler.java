package teamb;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

// Event handler for adding a new movies
class AddMovieHandler implements EventHandler<ActionEvent> {
    private TextField title,
            genre,
            duration,
            language;
    private DatePicker date;
    private ObservableList<String> list;

    public AddMovieHandler(TextField title, TextField genre, TextField duration, TextField language, DatePicker date, ObservableList<String> list) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.language = language;
        this.date = date;
        this.list = list;
    }
    
    @Override
    public void handle(ActionEvent t) {
        Movie.insertMovie(title.getText(), genre.getText(),
                language.getText(), Integer.parseInt(duration.getText()),
                date.getValue().getDayOfMonth(), date.getValue().getMonth().getValue(),
                date.getValue().getYear());
        
        var movies = Movie.getMovies();
        list.clear();
        for (var m : movies) {
            list.add(m.toString());
        }
    }
}
