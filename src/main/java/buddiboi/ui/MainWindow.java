package buddiboi.ui;

import buddiboi.BuddiBoi;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the retro-style BuddiBoi GUI.
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
    @FXML
    private Label typingIndicator;

    private BuddiBoi buddiBoi;
    private boolean awaitingSaveConfirmation = false;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image buddiBoiImage = new Image(this.getClass().getResourceAsStream("/images/BuddiBoi.png"));

    // Typing animation
    private Timeline typingTimeline;
    private int typingDotCount = 1;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        setupTypingAnimation();
    }

    private void setupTypingAnimation() {
        typingTimeline = new Timeline(new KeyFrame(Duration.millis(400), e -> {
            typingDotCount = (typingDotCount % 3) + 1;
            String dots = ".".repeat(typingDotCount);
            typingIndicator.setText("BuddiBoi is typing" + dots);
        }));
        typingTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void showTypingIndicator() {
        typingDotCount = 1;
        typingIndicator.setText("BuddiBoi is typing.");
        typingIndicator.setVisible(true);
        typingIndicator.setManaged(true);
        typingTimeline.play();
    }

    private void hideTypingIndicator() {
        typingTimeline.stop();
        typingIndicator.setVisible(false);
        typingIndicator.setManaged(false);
    }

    public void setBuddiBoi(BuddiBoi b) {
        buddiBoi = b;
        String welcome = buddiBoi.getWelcomeMessage();
        if (welcome != null && !welcome.isBlank()) {
            dialogContainer.getChildren().add(
                DialogBox.getBuddiBoiDialog(welcome, buddiBoiImage)
            );
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) return;

        userInput.setDisable(true);
        sendButton.setDisable(true);

        if (awaitingSaveConfirmation) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            userInput.clear();
            userInput.setDisable(false);
            sendButton.setDisable(false);
            handleSaveConfirmation(input);
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();

        showTypingIndicator();

        PauseTransition thinkDelay = new PauseTransition(Duration.millis(700 + Math.random() * 600));
        thinkDelay.setOnFinished(e -> {
            hideTypingIndicator();

            String response = buddiBoi.getResponse(input);
            dialogContainer.getChildren().add(DialogBox.getBuddiBoiDialog(response, buddiBoiImage));

            userInput.setDisable(false);
            sendButton.setDisable(false);
            userInput.requestFocus();

            if (buddiBoi.shouldExit()) {
                awaitingSaveConfirmation = true;
            }
        });
        thinkDelay.play();
    }

    private void handleSaveConfirmation(String input) {
        showTypingIndicator();

        PauseTransition pause = new PauseTransition(Duration.millis(600));
        pause.setOnFinished(e -> {
            hideTypingIndicator();

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

            PauseTransition exitPause = new PauseTransition(Duration.seconds(2));
            exitPause.setOnFinished(ev -> Platform.exit());
            exitPause.play();
        });
        pause.play();
    }
}
