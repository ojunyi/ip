package buddiboi.ui;

import java.io.IOException;

import buddiboi.BuddiBoi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Entry point for the JavaFX GUI application.
 * Loads the main window layout from FXML and initialises the application.
 */
public class Gui extends Application {

    private BuddiBoi buddiBoi = new BuddiBoi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("BuddiBoi");
            fxmlLoader.<MainWindow>getController().setBuddiBoi(buddiBoi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
