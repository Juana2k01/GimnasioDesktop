package Controllers;

import ENTITY.Empleado;
import Persistence.EmpleadoDAO;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VerEmpleadosController implements Initializable {


    @FXML
    private VBox vboxContenido;


    @FXML
    private BorderPane borderRaiz;

    private Stage stage;

    EmpleadoDAO empleadoDAO;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        vboxContenido.getChildren().clear();

        empleadoDAO = new EmpleadoDAO();
        try {
            Crearsecciones(empleadoDAO.contadorEmpleados());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void Crearsecciones(int cantidad) throws SQLException, IOException {
        vboxContenido.getChildren().clear();
        int minimo = 1;
        int maximo = cantidad;


        ArrayList<Empleado> Empleados = empleadoDAO.getEmpleados();
        for (int i = 0; i < maximo; i++) {
            if(Empleados.get(i).getRolid()!= 1) {
                AnchorPane nodoContenido = new AnchorPane();

                nodoContenido.setPrefSize(1078, 75);
                empleadoDAO = new EmpleadoDAO();
                Empleado empleado;
                empleado = Empleados.get(i);
                ImageView imageViewemp = transformar(empleado.getImagen());
                imageViewemp.getStyleClass().add("imagen");


                Circle clip = new Circle(25, 25, 25);
                imageViewemp.setClip(clip);


                Label label = new Label();
                label.setText(empleado.getNombre());
                nodoContenido.getChildren().addAll(label, imageViewemp);
                imageViewemp.setFitWidth(50);
                imageViewemp.setFitHeight(50);
                imageViewemp.setLayoutX(50);
                imageViewemp.setLayoutY(15);

                AnchorPane.setLeftAnchor(imageViewemp, 50.0);
                AnchorPane.setTopAnchor(imageViewemp, 15.0);

                label.setLayoutX(250);
                label.setLayoutY(50);

                ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), nodoContenido);
                scaleIn.setToX(1.020);
                scaleIn.setToY(1.205);


                ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), nodoContenido);
                scaleOut.setToX(1.0);
                scaleOut.setToY(1.0);


                nodoContenido.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        scaleIn.play();
                        nodoContenido.getStyleClass().add("panelContenido");
                        label.getStyleClass().add("NombreH");

                    } else {
                        scaleOut.play();
                        label.getStyleClass().remove("NombreH");
                        nodoContenido.getStyleClass().remove("panelContenido");

                    }
                });

                vboxContenido.getChildren().add(nodoContenido);
            }
        }


    }


    public ImageView transformar (byte[] imageData){

        InputStream inputStream = new ByteArrayInputStream(imageData);
        Image image = new Image(inputStream);


        ImageView imageView = new ImageView();
        imageView.setImage(image);
        return imageView;
    }




    public void setStage(Stage stage) {

        this.stage = stage;
        if (stage != null) {
            System.out.println("Stage inicializado correctamente en VerEmpleadosController.");
        } else {
            System.out.println("¡ADVERTENCIA! El Stage es null en VerEmpleadosController.");
        }
    }





}



