package Controllers;

import ENTITY.Empleado;
import ENTITY.Usuario_ADM;
import Persistence.Usuario_ADMDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    private Stage stage;
    private FXMLLoader loader;
    Usuario_ADM usuarioAdm;
    Usuario_ADMDAO usuario_admdao;
    private  Scene scene;
    @FXML
    private TextField usuario_login;

    @FXML
    private PasswordField passfield;

    private AdminController adminController;



    public LoginController() {
        usuario_admdao = new Usuario_ADMDAO();
        usuarioAdm = new Usuario_ADM();

    }
    public void login(ActionEvent actionEvent) throws IOException, SQLException {
        usuarioAdm.setUsername(usuario_login.getText());
        usuarioAdm.setPassword(passfield.getText());
        if (usuario_admdao.VerificacionUsuario(usuarioAdm)){

            Empleado empleado = usuario_admdao.getEmpleado(usuarioAdm);
            if (empleado.getRolid() == 1) {
                loader = new FXMLLoader(getClass().getResource("/templates/MenuAdmin.fxml"));
                Parent root = loader.load();
                 adminController = loader.getController();
                adminController.setStage(stage);
                adminController.EmpleadoInciado(empleado);
                scene = new Scene(root);
            } else if (empleado.getRolid() == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Formulario_Regristo.fxml"));
                Parent root = loader.load();

                scene = new Scene(root);

            }else {
                System.out.println("Usuario no existe");
            }


            stage.setScene(scene);
            stage.setTitle("Página de Inicio");

            stage.setResizable(true);
            stage.setMaximized(true);


            stage.show();
        } else {
            mostrarAlerta("Contraseña Incorrecta", "Ingresa la contraseña adecuada",Alert.AlertType.ERROR);

        }


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }




    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void registrarme(ActionEvent actionEvent) {
    }


}
