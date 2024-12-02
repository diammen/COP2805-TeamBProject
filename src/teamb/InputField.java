// Marcel Dao
package teamb;

import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class InputField extends Pane {
    private VBox column;
    private TextField field;
    private Label label;
    
    public InputField() {
        this("Input");
    }
    public InputField(String label) {
        this.column = new VBox();
        this.field = new TextField();
        this.label = new Label();
        
        column.getChildren().addAll(this.label, this.field);
        this.label.setText(label);
        this.getChildren().add(this.column);
    }
    public TextField getField() {
        return field;
    }
    public Label getLabel() {
        return label;
    }
}
