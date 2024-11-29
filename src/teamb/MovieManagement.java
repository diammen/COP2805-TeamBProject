package teamb;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieManagement extends Application {
    
    BorderPane mainPane = new BorderPane();
    TabPane tabPane = new TabPane();
    HBox bottomRow = new HBox(10);

    @Override
    public void start(Stage stage) {
        
        // Initializing UI nodes to set up the layout
        MovieTab buyTicketTab = new MovieTab("Buy Tickets");
        Button buyTicketButton = new Button("Buy Movie Ticket");
        BorderPane ticketBPane = new BorderPane();
        VBox rightCol = new VBox(10);
        
        // Setting up values to create the layout
        rightCol.getChildren().add(buyTicketButton);
        rightCol.setPadding(new Insets(25, 25, 25, 25));
        ticketBPane.setRight(rightCol);
        ticketBPane.setMinSize(250, 250);
        buyTicketTab.setMainPane(ticketBPane);
        
        // Initializing UI nodes to set up the layout
        MovieTab addMovieTab = new MovieTab("Movies");
        BorderPane movieBPane = new BorderPane();
        ListView movieListView = new ListView();
        HBox addMovieBox = new HBox(10);
        TextField addMovieField = new TextField();
        
        MovieTab showtimeTab = new MovieTab("Showtimes");
        BorderPane showtimeBPane = new BorderPane();
        ListView showtimeView = new ListView();
        
        showtimeTab.setMainPane(showtimeBPane);
        
        // Display list of movies
        ObservableList<String> movies = FXCollections.observableArrayList("Godzilla Minus One", "Django Unchained", "Iron Man");
        movieListView.setItems(movies);
        
        // Event handler is assigned to add movie button
        Button addMovieButton = new Button("Add Movie");
        addMovieButton.setOnAction(new AddMovieHandler(addMovieField, movies));
        
        // Setting up values to create the layout
        addMovieBox.setPadding(new Insets(20, 20, 20, 20));
        addMovieBox.getChildren().addAll(addMovieField, addMovieButton);
        movieBPane.setPadding(new Insets(20, 20, 20, 20));
        movieBPane.setCenter(movieListView);
        movieBPane.setBottom(addMovieBox);
        movieBPane.setMinSize(250, 250);
        addMovieTab.setMainPane(movieBPane);
        
        tabPane.getTabs().addAll(buyTicketTab, addMovieTab);
        
        tabPane.setMaxSize(500, 500);
        bottomRow.setAlignment(Pos.CENTER);
        
        mainPane.setMaxSize(500, 250);
        mainPane.setCenter(tabPane);
        mainPane.setBottom(bottomRow);
        
        Scene scene = new Scene(mainPane);
        stage.setTitle("Movie Management System");
        stage.setScene(scene);
        stage.show();
    }
    
    // Event handler for adding a new movie
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
    
    public static void main(String[] args) {
        launch(args);
    }
}
