// edu.easternflorida.MarcelD
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
    
    private static final double PADDING = 20;
    private static final double BP_SIZE = 500;
    BorderPane mainPane = new BorderPane();
    TabPane tabPane = new TabPane();
    HBox bottomRow = new HBox(10);

    @Override
    public void start(Stage stage) {
        
        // Initializing UI nodes to set up the layout
        MovieTab buyTicketTab = new MovieTab("Buy Tickets");
        Button buyTicketButton = new Button("Buy Movie Ticket");
        BorderPane ticketBPane = new BorderPane();
        InputField nameField = new InputField("Name");
        InputField emailField = new InputField("E-mail");
        InputField phoneField = new InputField("Phone Number");
        InputField addressField = new InputField("Address");
        InputField movieField = new InputField("Movie");
        VBox ticketCenterCol = new VBox(10);
        VBox ticketRightCol = new VBox(10);
        
        // Setting up values to create the layout
        ticketCenterCol.getChildren().addAll(nameField, emailField, phoneField, addressField);
        ticketCenterCol.setPadding(new Insets(PADDING));
        ticketRightCol.getChildren().addAll(movieField, buyTicketButton);
        ticketRightCol.setPadding(new Insets(PADDING));
        ticketRightCol.setAlignment(Pos.TOP_RIGHT);
        ticketBPane.setCenter(ticketCenterCol);
        ticketBPane.setRight(ticketRightCol);
        //ticketBPane.setMinSize(BP_SIZE, BP_SIZE);
        buyTicketTab.setMainPane(ticketBPane);
        
        // Initializing UI nodes to set up the layout
        MovieTab addMovieTab = new MovieTab("Movies");
        BorderPane movieBPane = new BorderPane();
        ListView movieListView = new ListView();
        InputField movieTitleField = new InputField("Movie Title");
        InputField movieGenreField = new InputField("Movie Genre");
        HBox addMovieBox = new HBox(10);
        VBox movieRightCol = new VBox(10);
        
        // Display list of movies
        ObservableList<String> movies = FXCollections.observableArrayList("Godzilla Minus One", "Django Unchained", "Iron Man");
        movieListView.setItems(movies);
        
        // Event handler is assigned to add movie button
        Button addMovieButton = new Button("Add Movie");
        addMovieButton.setOnAction(new AddMovieHandler(movieTitleField.getField(), movies));
        
        // Setting up values to create the layout
        addMovieBox.setPadding(new Insets(PADDING));
        addMovieBox.getChildren().addAll(movieTitleField, addMovieButton);
        movieBPane.setPadding(new Insets(PADDING));
        movieBPane.setCenter(movieListView);
        movieBPane.setBottom(addMovieBox);
        //movieBPane.setMinSize(BP_SIZE, BP_SIZE);
        addMovieTab.setMainPane(movieBPane);
        
        MovieTab showtimeTab = new MovieTab("Showtimes");
        BorderPane showtimeBPane = new BorderPane();
        ListView showtimeView = new ListView();
        
        showtimeBPane.setCenter(showtimeView);
        //showtimeBPane.setMinSize(BP_SIZE, BP_SIZE);
        showtimeTab.setMainPane(showtimeBPane);
        
        tabPane.getTabs().addAll(buyTicketTab, addMovieTab, showtimeTab);
        
        //tabPane.setMaxSize(500, 500);
        bottomRow.setAlignment(Pos.CENTER);
        
        mainPane.setMaxSize(500, 300);
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
