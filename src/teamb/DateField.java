package teamb;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DateField extends Pane {

    private VBox column;
    private DatePicker date;
    private Label label;

    public DateField() {
        this("Input");
    }

    public DateField(String label) {
        this.column = new VBox();
        this.date = new DatePicker();
        this.label = new Label();

        column.getChildren().addAll(this.label, this.date);
        this.label.setText(label);
        this.getChildren().add(this.column);
    }

    public DatePicker getDatePicker() {
        return date;
    }

    public Label getLabel() {
        return label;
    }
}
