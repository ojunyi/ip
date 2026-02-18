package buddiboi.ui;

import java.io.IOException;

import buddiboi.BuddiBoi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
