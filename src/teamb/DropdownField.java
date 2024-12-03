// Marcel Dao
package teamb;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DropdownField extends Pane {
    private VBox column;
    private ComboBox box;
    private Label label;
    
    public DropdownField() {
        this("Input");
    }
    public DropdownField(String label) {
        this.column = new VBox();
        this.box = new ComboBox();
        this.label = new Label();
        
        column.getChildren().addAll(this.label, this.box);
        this.label.setText(label);
        this.getChildren().add(this.column);
    }
    public ComboBox getField() {
        return box;
    }
    public Label getLabel() {
        return label;
    }
}
