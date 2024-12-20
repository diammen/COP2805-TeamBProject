// edu.easternflorida.MarcelD
package teamb;

import static java.lang.String.format;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

public class MovieManagement extends Application {

    private static final double PADDING = 20;
    private static final double BP_SIZE = 500;
    BorderPane mainPane = new BorderPane();
    TabPane tabPane = new TabPane();
    HBox bottomRow = new HBox(10);

    @Override
    public void start(Stage stage) {
        // Create the database if it doesn't exist
        MovieTheaterDbSetup.CreateDatabase();
        for (int i = 0; i < 4; i++) {
            ScreenInserter.insertScreens(i);
        }
        
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm");

        ObservableList<String> tickets = FXCollections.observableArrayList();

        ObservableList<String> movies = FXCollections.observableArrayList();
        ObservableList<String> movieNames = FXCollections.observableArrayList();

        ObservableList<String> showtimes = FXCollections.observableArrayList();
        var showtimesList = Showtime.getShowtimes();

        // Initializing UI nodes to set up the layout
        MovieTab buyTicketTab = new MovieTab("Buy Tickets");
        Button buyTicketButton = new Button("Buy Movie Ticket");
        BorderPane ticketBPane = new BorderPane();
        InputField nameField = new InputField("Name");
        InputField emailField = new InputField("E-mail");
        InputField phoneField = new InputField("Phone Number");
        InputField addressField = new InputField("Address");
        DropdownField movieField = new DropdownField("Movie");
        DateField buyDateField = new DateField("Calendar Date");
        InputField buyClockTimeField = new InputField("Clock Time (HH::MM)");
        InputField seatField = new InputField("Seat #");
        VBox ticketCenterCol = new VBox(10);
        VBox ticketRightCol = new VBox(10);
        
        movieField.getField().setItems(movieNames);
        movieField.getField().getSelectionModel().selectFirst();
        
        buyClockTimeField.getField().setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(timeDateFormat)));

        buyTicketButton.setOnAction(new BuyTicketHandler(nameField.getField(), emailField.getField(),
                phoneField.getField(), addressField.getField(), movieField,
                buyDateField, buyClockTimeField.getField(), seatField.getField()));

        // Setting up values to create the layout
        ticketCenterCol.getChildren().addAll(nameField,
                emailField, phoneField, addressField);
        ticketCenterCol.setPadding(new Insets(PADDING));
        ticketRightCol.getChildren().addAll(movieField,
                buyDateField, buyClockTimeField,
                seatField, buyTicketButton);
        ticketRightCol.setPadding(new Insets(PADDING));
        ticketRightCol.setAlignment(Pos.TOP_RIGHT);
        ticketBPane.setCenter(ticketCenterCol);
        ticketBPane.setRight(ticketRightCol);
        buyTicketTab.setMainPane(ticketBPane);

        // Initializing UI nodes to set up the layout
        MovieTab addMovieTab = new MovieTab("Movies");
        BorderPane movieBPane = new BorderPane();
        ListView movieListView = new ListView();
        InputField movieTitleField = new InputField("Movie Title");
        InputField movieGenreField = new InputField("Movie Genre");
        InputField movieDurationField = new InputField("Duration");
        InputField movieLanguageField = new InputField("Language");
        DateField movieReleaseDateField = new DateField("Release Date");
        VBox movieInputCol = new VBox(20);
        HBox addMovieBox = new HBox(10);

        // Display list of movies
        movieListView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setPrefWidth(100);
                    setWrapText(true);
                    setText(item.toString());
                }
            }
        });

        var moviesList = Movie.getMovies();
        for (var m : moviesList) {
            movies.add(m.toString());
            movieNames.add(m.getTitle());
        }
        movieListView.setItems(movies);

        // Event handler is assigned to add movie button
        Button addMovieButton = new Button("Add Movie");
        addMovieButton.setOnAction(new AddMovieHandler(movieTitleField.getField(),
                movieGenreField.getField(),
                movieDurationField.getField(),
                movieLanguageField.getField(),
                movieReleaseDateField.getDatePicker(),
                movies, movieNames));

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
        addMovieTab.setMainPane(movieBPane);

        // Showtimes
        MovieTab showtimeTab = new MovieTab("Showtimes");
        BorderPane showtimeBPane = new BorderPane();
        ListView showtimeView = new ListView();
        DropdownField showtimeScreenField = new DropdownField("Screen #");
        DateField showtimeDateField = new DateField("Calendar Date");
        InputField showtimeClockTimeField = new InputField("Clock Time (HH::MM)");
        DropdownField showtimeMovieField = new DropdownField("Movie");
        InputField showtimePriceField = new InputField("Price");
        Button showtimeAddButton = new Button("Add Showtime");
        VBox showtimeInputBox = new VBox(10);

        ObservableList<Integer> screens = FXCollections.observableArrayList();
        screens.addAll(1, 2, 3, 4);
        showtimeScreenField.getField().setItems(screens);
        showtimeScreenField.getField().getSelectionModel().selectFirst();

        showtimeClockTimeField.getField().setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(timeDateFormat)));

        showtimeMovieField.getField().setItems(movieNames);
        showtimeMovieField.getField().getSelectionModel().selectFirst();

        showtimeInputBox.setPadding(new Insets(PADDING));
        showtimeInputBox.getChildren().addAll(showtimeScreenField, showtimeDateField, showtimeClockTimeField,
                showtimeMovieField, showtimePriceField, showtimeAddButton);

        for (var s : showtimesList) {
            showtimes.add(s.toString());
        }
        System.out.println(showtimes.size());
        showtimeView.setItems(showtimes);

        showtimeAddButton.setOnAction(new AddShowtimeHandler(showtimeScreenField, showtimeDateField.getDatePicker(),
                showtimeClockTimeField.getField(),
                showtimeMovieField,
                showtimePriceField.getField(), showtimes));

        // Display list of showtimes
        showtimeView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setPrefWidth(100);
                    setWrapText(true);
                    setText(item.toString());
                }
            }
        });
        showtimeBPane.setCenter(showtimeView);
        showtimeBPane.setRight(showtimeInputBox);
        showtimeBPane.setPadding(new Insets(PADDING));
        showtimeTab.setMainPane(showtimeBPane);

        // Customers List
        MovieTab customerTab = new MovieTab("Customers");
        BorderPane customerBPane = new BorderPane();
        Label customerCount = new Label();
        Button getCustomerCountButton = new Button("Get Occupant Count");
        ListView customerFocusView = new ListView();
        InputField customerNameField = new InputField("Customer Name");
        Label customerFoundLabel = new Label();
        Button findCustomerButton = new Button("Find Customer");
        HBox findCustomerBox = new HBox(10);
        VBox customerFocusBox = new VBox(10);
        VBox customerCountBox = new VBox(10);

        customerCount.setText("Theater Occupants: 0");
        customerCountBox.getChildren().addAll(customerCount, getCustomerCountButton);
        getCustomerCountButton.setOnAction(new GetOccupantsHandler(customerCount));

        
        // Setting TextArea to be readonly
        customerFocusView.setMaxSize(200, 300);
        customerFocusView.setEditable(false);
        customerFocusView.setMouseTransparent(true);
        customerFocusView.setFocusTraversable(false);
        

        findCustomerButton.setOnAction(new FindCustomerHandler(customerNameField.getField(), customerFocusView, customerFoundLabel, tickets));

        findCustomerBox.getChildren().addAll(customerNameField, findCustomerButton);
        findCustomerBox.setPadding(new Insets(PADDING));
        customerFocusBox.getChildren().addAll(customerFocusView, findCustomerBox);
        customerFocusBox.setPadding(new Insets(PADDING));
        customerBPane.setPadding(new Insets(PADDING));
        customerBPane.setCenter(customerCountBox);
        customerBPane.setRight(customerFocusBox);
        customerTab.setMainPane(customerBPane);

        tabPane.getTabs().addAll(buyTicketTab, addMovieTab, showtimeTab, customerTab);

        //tabPane.setMaxSize(500, 500);
        bottomRow.setAlignment(Pos.CENTER);

        mainPane.setMaxSize(500, 450);
        mainPane.setCenter(tabPane);
        mainPane.setBottom(bottomRow);

        Scene scene = new Scene(mainPane);
        stage.setTitle("Movie Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
