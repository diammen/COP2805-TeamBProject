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
import javafx.scene.control.ListView;
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
        InputField movieDurationField = new InputField("Duration");
        InputField movieLanguageField = new InputField("Language");
        InputField movieReleaseDateField = new InputField("Release Date");
        VBox movieInputCol = new VBox(20);
        HBox addMovieBox = new HBox(10);
        
        // Display list of movies
        ObservableList<String> movies = FXCollections.observableArrayList("Godzilla Minus One", "Django Unchained", "Iron Man");
        movieListView.setItems(movies);
        
        // Event handler is assigned to add movie button
        Button addMovieButton = new Button("Add Movie");
        addMovieButton.setOnAction(new AddMovieHandler(movieTitleField.getField(), movies));
        
        // Setting up values to create the layout
        movieInputCol.getChildren().addAll(movieTitleField,
                movieGenreField, movieDurationField,
                movieLanguageField, movieReleaseDateField);
        addMovieBox.setPadding(new Insets(PADDING));
        addMovieBox.getChildren().addAll(movieInputCol, addMovieButton);
        addMovieBox.setAlignment(Pos.BOTTOM_LEFT);
        movieBPane.setPadding(new Insets(PADDING));
        movieBPane.setCenter(movieListView);
        movieBPane.setRight(addMovieBox);
        //movieBPane.setMinSize(BP_SIZE, BP_SIZE);
        addMovieTab.setMainPane(movieBPane);
        
        // Showtimes
        MovieTab showtimeTab = new MovieTab("Showtimes");
        BorderPane showtimeBPane = new BorderPane();
        ListView showtimeView = new ListView();
        
        showtimeView.setPadding(new Insets(PADDING));
        showtimeBPane.setCenter(showtimeView);
        showtimeBPane.setPadding(new Insets(PADDING));
        showtimeTab.setMainPane(showtimeBPane);
        
        // Customers List
        MovieTab customerTab = new MovieTab("Customers");
        BorderPane customerBPane = new BorderPane();
        ListView customerListView = new ListView();
        TextArea customerFocusView = new TextArea();
        InputField customerNameField = new InputField("Customer Name");
        Button findCustomerButton = new Button("Find Customer");
        HBox findCustomerBox = new HBox(10);
        VBox customerFocusBox = new VBox(10);
        
        // Setting TextArea to be readonly
        customerFocusView.setMaxSize(150, 300);
        customerFocusView.setEditable(false);
        customerFocusView.setMouseTransparent(true);
        customerFocusView.setFocusTraversable(false);
        
        findCustomerBox.getChildren().addAll(customerNameField, findCustomerButton);
        findCustomerBox.setPadding(new Insets(PADDING));
        customerFocusBox.getChildren().addAll(customerFocusView, findCustomerBox);
        customerFocusBox.setPadding(new Insets(PADDING));
        customerBPane.setPadding(new Insets(PADDING));
        customerBPane.setCenter(customerListView);
        customerBPane.setRight(customerFocusBox);
        customerTab.setMainPane(customerBPane);
        
        tabPane.getTabs().addAll(buyTicketTab, addMovieTab, showtimeTab, customerTab);
        
        //tabPane.setMaxSize(500, 500);
        bottomRow.setAlignment(Pos.CENTER);
        
        mainPane.setMaxSize(500, 350);
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
