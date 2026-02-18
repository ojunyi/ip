package buddiboi.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Represents a dialog box
 */
public class DialogBox extends VBox {
    @FXML
    private Label speakerLabel;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String speaker, String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        speakerLabel.setText(isUser ? "YOU" : "BUDDIBOI");
        dialog.setText(text);
        displayPicture.setImage(img);

        if (isUser) {
            dialog.setStyle(dialog.getStyle() + "; -fx-text-fill: #FFFFFF;");
            speakerLabel.setStyle(speakerLabel.getStyle() + "; -fx-text-fill: #AAFFAA;");
        } else {
            dialog.setStyle(dialog.getStyle() + "; -fx-text-fill: #5BC8F5;");
            speakerLabel.setStyle(speakerLabel.getStyle() + "; -fx-text-fill: #FFD700;");
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox("YOU", text, img, true);
    }

    public static DialogBox getBuddiBoiDialog(String text, Image img) {
        return new DialogBox("BUDDIBOI", text, img, false);
    }
}
