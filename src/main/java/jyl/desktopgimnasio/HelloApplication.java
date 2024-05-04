package jyl.desktopgimnasio;

import Controllers.LoginController;
import Controllers.VerEmpleadosController;
import Utils.db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/LOGIN.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Iniciar Sesión");
        stage.setMaximized(false);
        stage.setResizable(false);

        Image icono = new Image(String.valueOf(getClass().getResource("/img/loguito.png")));
        stage.getIcons().add(icono);





        stage.setScene(scene);

       LoginController controller = loader.getController();
        controller.setStage(stage);



        stage.show();





    }

    public static void main(String[] args) {





        launch();


    }
}