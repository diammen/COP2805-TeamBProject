
package teamb;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

// Event handler for adding a new movies
class AddMovieHandler implements EventHandler<ActionEvent> {
    TextField field;
    ObservableList<String> list;

    public AddMovieHandler(TextField field, ObservableList<String> list) {
        this.field = field;
        this.list = list;
    }
    @Override
    public void handle(ActionEvent t) {
        // Add SQL report to add movie to database here
        list.add(field.getText());
    }

}
