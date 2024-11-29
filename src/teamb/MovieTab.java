package teamb;

import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

public class MovieTab extends Tab {
    
    private Pane mainPane = new Pane();
    
    public MovieTab() {
        super();
        this.setClosable(false);
        this.setContent(mainPane);
    }
    public MovieTab(String text) {
        super(text);
        this.setClosable(false);
        this.setContent(mainPane);
    }
    public void setMainPane(Pane pane) {
        this.mainPane = pane;
        this.setContent(mainPane);
    }
    public Pane getMainPane() {
        return this.mainPane;
    }
}
