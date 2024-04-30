package jyl.desktopgimnasio;

import Utils.db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/LOGIN.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("LOGIN");
        stage.setMaximized(false);
        stage.setResizable(false);

        stage.setScene(scene);


        Controllers.LoginController controller = loader.getController();
        controller.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {





        launch();


    }
}