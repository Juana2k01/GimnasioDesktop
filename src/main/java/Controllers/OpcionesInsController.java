package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.io.IOException;

public class OpcionesInsController {

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







}
