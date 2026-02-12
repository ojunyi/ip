package buddiboi.ui;

import buddiboi.BuddiBoi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BuddiBoi buddiBoi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image buddiBoiImage = new Image(this.getClass().getResourceAsStream("/images/BuddiBoi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the BuddiBoi instance */
    public void setBuddiBoi(BuddiBoi b) {
        buddiBoi = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing BuddiBoi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = buddiBoi.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBuddiBoiDialog(response, buddiBoiImage)
        );
        userInput.clear();
    }
}
