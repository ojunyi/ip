package buddiboi.ui;

import buddiboi.BuddiBoi;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
    private boolean awaitingSaveConfirmation = false;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image buddiBoiImage = new Image(this.getClass().getResourceAsStream("/images/BuddiBoi.png"));

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

        if (awaitingSaveConfirmation) {
            handleSaveConfirmation(input);
            userInput.clear();
            return;
        }

        String response = buddiBoi.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBuddiBoiDialog(response, buddiBoiImage)
        );
        userInput.clear();

        if (buddiBoi.shouldExit()) {
            awaitingSaveConfirmation = true;
        }
    }

    private void handleSaveConfirmation(String input) {

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        if (input.equalsIgnoreCase("yes")) {
            buddiBoi.save();
            dialogContainer.getChildren().add(
                    DialogBox.getBuddiBoiDialog(Ui.showSaveConfirmation(true), buddiBoiImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getBuddiBoiDialog(Ui.showSaveConfirmation(false), buddiBoiImage)
            );
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }
}
