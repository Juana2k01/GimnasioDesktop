package Controllers;

import ENTITY.Empleado;
import ENTITY.Usuario_ADM;
import Persistence.EmpleadoDAO;
import Persistence.Usuario_ADMDAO;
import Utils.ContraAutomatica;
import Utils.Correos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


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
    private Label Labeltitulo;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labeltituloCargo;

    @FXML
    private Label labelEmail;


    @FXML
    private BorderPane borderRaiz;

    private Stage stage;

    private DetallesPersonaController Detalles;
    private AnchorPane nodoContenido;

    private Boolean respuesta;

    private boolean rightContentLoaded = false;
    EmpleadoDAO empleadoDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        vboxContenido.getChildren().clear();

        empleadoDAO = new EmpleadoDAO();
        try {
            CrearSecciones(empleadoDAO.contadorEmpleados());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }






    }


    private void CrearSecciones(int cantidad) throws SQLException, IOException {
        vboxContenido.getChildren().clear();

        int maximo = cantidad;

        ArrayList<Empleado> Empleados = empleadoDAO.getEmpleados();
        for (int i = 0; i < maximo; i++) {

            if (Empleados.get(i).getRolid() != 1) {
               AnchorPane nodoContenido = new AnchorPane();

                nodoContenido.setPrefSize(1078, 75);
                nodoContenido.getStyleClass().add("panelContenido");
                empleadoDAO = new EmpleadoDAO();
                Empleado empleado;
                empleado = Empleados.get(i);
                ImageView imageViewemp = transformar(empleado.getImagen());
                imageViewemp.getStyleClass().add("imagen");


                Circle clip = new Circle(25, 25, 25);
                imageViewemp.setClip(clip);


                Label label = new Label(empleado.getNombre() + " " + empleado.getApellido());

                label.getStyleClass().add("Nombre");

                Label labelCargo = new Label();
                if (empleado.getRolid() == 2) {

                    labelCargo.setText("Gerente");
                } else {
                    labelCargo.setText("Monitor");
                }

                labelCargo.getStyleClass().add("Nombre");

                Label emailEmpleado = new Label(empleado.getEmail());
                emailEmpleado.getStyleClass().add("Nombre");


                nodoContenido.getChildren().addAll(label, imageViewemp, labelCargo);


                imageViewemp.setFitWidth(50);
                imageViewemp.setFitHeight(50);
                imageViewemp.setLayoutX(50);
                imageViewemp.setLayoutY(12.5);
                AnchorPane.setLeftAnchor(imageViewemp, 50.0);
                AnchorPane.setTopAnchor(imageViewemp, 12.5);


                label.setLayoutX(250);
                label.setLayoutY(30);
                AnchorPane.setLeftAnchor(label, 250.0);
                AnchorPane.setTopAnchor(label, 30.0);


                labelCargo.setLayoutX(480);
                labelCargo.setLayoutY(30);
                AnchorPane.setLeftAnchor(labelCargo, 480.0);
                AnchorPane.setTopAnchor(labelCargo, 30.0);


                emailEmpleado.setLayoutX(630);
                emailEmpleado.setLayoutY(30);
                AnchorPane.setLeftAnchor(emailEmpleado, 630.0);
                AnchorPane.setTopAnchor(emailEmpleado, 30.0);


                nodoContenido.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {

                        nodoContenido.getStyleClass().add("panelContenidoHover");
                        label.getStyleClass().add("NombreH");
                        labelCargo.getStyleClass().add("NombreH");
                        emailEmpleado.getStyleClass().add("NombreH");


                    } else {

                        label.getStyleClass().remove("NombreH");
                        nodoContenido.getStyleClass().remove("panelContenidoHover");
                        labelCargo.getStyleClass().remove("NombreH");
                        emailEmpleado.getStyleClass().remove("NombreH");

                    }
                });


                nodoContenido.setOnMouseClicked(event -> {
                    try {
                       handleAnchorEmpleado(empleado);
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                });


                vboxContenido.getChildren().add(nodoContenido);

            }


        }
            actualizarScrollPane();
    }

    private void actualizarScrollPane() {
        if (vboxContenido.getParent() != null) {
            vboxContenido.getParent().requestLayout();
        }
    }


    private void handleAnchorEmpleado(Empleado empleado) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/detallesPersona.fxml"));
        Parent rightContent = loader.load();

        Detalles = loader.getController();
        Detalles.EmpleadoDetalle(empleado);
        Detalles.setVerEmpleadosController(this);
        if (borderRaiz.getRight() != null) {


        } else {

            borderRaiz.setRight(rightContent);


            for (Node child : vboxContenido.getChildren()) {
                if (child instanceof Region) {
                    ((Region) child).setPrefWidth(710);
                }
            }


        }




    }


    public ImageView transformar(byte[] imageData) {

        InputStream inputStream = new ByteArrayInputStream(imageData);
        Image image = new Image(inputStream);


        ImageView imageView = new ImageView();
        imageView.setImage(image);
        return imageView;
    }


    public void setStage(Stage stage) {

        this.stage = stage;

    }


    public void cerrarPanel() {
        borderRaiz.setRight(null);
        for (Node child : vboxContenido.getChildren()) {
            if (child instanceof Region) {
                ((Region) child).setPrefWidth(1078);
            }
        }
    }
}



