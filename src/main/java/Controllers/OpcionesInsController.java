package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

import java.io.IOException;

public class OpcionesInsController {

    @FXML private Button botonCliente;
    @FXML private Button botonEmpleados;

AdminController adminController;


    public void setAdminController(AdminController controller) {
        this.adminController = controller;
    }

    public void formularioEmpleados(ActionEvent actionEvent) throws IOException {
        if (adminController != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/RegistroEmpleado.fxml"));
            Node contenido = loader.load();
            adminController.cargarFormularioCliente(contenido);
        } else {
            System.err.println("El controlador de administrador es nulo.");
        }
    }

    public void formularioCliente(ActionEvent actionEvent) {

    }


    public void initialize() {


        botonHover(botonCliente);
        botonHover(botonEmpleados);

    }


    public void botonHover(Button button){

        button.setOnMouseEntered(event -> {
            button.getStyleClass().add("btn_loginH");
        });


        button.setOnMouseExited(event -> {
            button.getStyleClass().remove("btn_loginH");
        });
    }




}
