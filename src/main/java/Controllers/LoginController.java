package Controllers;

import ENTITY.Empleado;
import ENTITY.Usuario_ADM;
import Persistence.Usuario_ADMDAO;
import Utils.ContraAutomatica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private Stage stage;
    private Usuario_ADMDAO usuario_admdao;
    private Scene scene;

    @FXML private TextField usuario_login;
    @FXML private PasswordField passfield;

    public LoginController() {
        usuario_admdao = new Usuario_ADMDAO();
    }

    public void login(ActionEvent actionEvent) {
        try {
            String username = usuario_login.getText();
            String passwordHash = ContraAutomatica.generarHashSHA256(passfield.getText());

            Usuario_ADM usuarioAdm = new Usuario_ADM();
            usuarioAdm.setUsername(username);
            usuarioAdm.setPassword(passwordHash);

            if (usuario_admdao.VerificacionUsuario(usuarioAdm)) {
                Empleado empleado = usuario_admdao.getEmpleado(usuarioAdm);
                String fxmlPath = (empleado.getRolid() == 1) ? "/templates/MenuAdmin.fxml" : "/templates/Formulario_Regristo.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();
                if (empleado.getRolid() == 1) {
                    AdminController adminController = loader.getController();
                    adminController.setStage(stage);
                    adminController.EmpleadoInciado(empleado);
                }
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Página de Inicio");
                stage.setResizable(true);
                stage.setMaximized(true);
                stage.show();
            } else {
                mostrarAlerta("Contraseña Incorrecta", "Ingresa la contraseña adecuada", Alert.AlertType.ERROR);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Ocurrió un error durante el inicio de sesión.", Alert.AlertType.ERROR);
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

    public void initialize() {
        passfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login(null);
            }
        });
    }
}
